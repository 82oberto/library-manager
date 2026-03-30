package com.roberto.library_manager.model.user;

import lombok.Builder;
import lombok.Data;

/**
 * DTO representing a user response.
 * Exposes only safe fields, excluding the password.
 */
@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
