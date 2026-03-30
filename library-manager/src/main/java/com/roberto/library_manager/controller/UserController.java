package com.roberto.library_manager.controller;

import com.roberto.library_manager.command.user.*;
import com.roberto.library_manager.model.user.User;
import com.roberto.library_manager.model.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing users.
 * Provides endpoints for creating, retrieving, updating and deleting users.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UpdateUserCommand updateUserCommand;
    private final GetOneUserCommand getOneUserCommand;
    private final DeleteOneUserCommand deleteOneUserCommand;
    private final DeleteAllUsersCommand deleteAllUsersCommand;
    private final GetAllUsersCommand getAllUsersCommand;
    private final InsertUserCommand insertUserCommand;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = getAllUsersCommand.execute(null);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getOneUser(@PathVariable String email) {
        UserResponse user = getOneUserCommand.execute(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponse> insertUser(@Valid @RequestBody User user) {
        UserResponse savedUser = insertUserCommand.execute(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String email, @Valid @RequestBody User user) {
        UserResponse updatedUser = updateUserCommand.execute(Map.entry(email, user));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteOneUser(@PathVariable String email) {
        deleteOneUserCommand.execute(email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        deleteAllUsersCommand.execute(null);
        return ResponseEntity.noContent().build();
    }
}