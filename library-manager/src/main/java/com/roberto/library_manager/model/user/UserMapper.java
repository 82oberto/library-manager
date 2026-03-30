package com.roberto.library_manager.model.user;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper for converting between User entity and UserResponse DTO.
 */
@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }
}
