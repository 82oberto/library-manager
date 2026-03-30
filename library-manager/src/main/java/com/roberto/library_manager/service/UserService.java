package com.roberto.library_manager.service;

import com.roberto.library_manager.exception.UserNotFoundException;
import com.roberto.library_manager.model.user.User;
import com.roberto.library_manager.model.user.UserMapper;
import com.roberto.library_manager.model.user.UserRequest;
import com.roberto.library_manager.model.user.UserResponse;
import com.roberto.library_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserResponse save(UserRequest request) {
        return userMapper.toResponse(repository.save(userMapper.toEntity(request)));
    }

    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    public UserResponse update(String email, UserRequest request) {
        Optional<User> existingUser = repository.findByEmail(email);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        User user = existingUser.get();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return userMapper.toResponse(repository.save(user));
    }

    public List<UserResponse> getAll() {
        return userMapper.toResponseList(repository.findAll());
    }

    public UserResponse getOne(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        return userMapper.toResponse(user.get());
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
