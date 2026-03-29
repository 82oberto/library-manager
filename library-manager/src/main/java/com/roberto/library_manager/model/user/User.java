package com.roberto.library_manager.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username must not exceed 20 characters")
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank(message = "Email is required")
    @Size(max = 30, message = "Email must not exceed 30 characters")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters and contain at least one letter and one number"
    )
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
