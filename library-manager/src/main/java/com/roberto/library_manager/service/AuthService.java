package com.roberto.library_manager.service;

import com.roberto.library_manager.exception.BookNotFoundException;
import com.roberto.library_manager.exception.InputException;
import com.roberto.library_manager.exception.UserNotFoundException;
import com.roberto.library_manager.model.user.Role;
import com.roberto.library_manager.model.user.User;
import com.roberto.library_manager.model.user.UserMapper;
import com.roberto.library_manager.model.user.UserRequest;
import com.roberto.library_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public String register(UserRequest user) {
        User userEntity = userMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
        return jwtService.generateToken(userEntity);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InputException("Invalid password");
        }
        return jwtService.generateToken(user);
    }
}
