package com.roberto.library_manager.service;

import com.roberto.library_manager.exception.UserNotFoundException;
import com.roberto.library_manager.model.user.*;
import com.roberto.library_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserRequest request) {
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);
        return userMapper.toResponse(repository.save(user));
    }

    public UserResponse update(String email, UserRequest request) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);

        return userMapper.toResponse(repository.save(user));
    }

    public List<UserResponse> getAll() {
        return userMapper.toResponseList(repository.findAll());
    }

    public UserResponse getOne(String email) {
        return userMapper.toResponse(repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)));
    }

    public void delete(String email) {
        repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        repository.deleteByEmail(email);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}