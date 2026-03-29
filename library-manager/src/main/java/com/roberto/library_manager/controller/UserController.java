package com.roberto.library_manager.controller;

import com.roberto.library_manager.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers(){
        return List.of(new User());
    }
    @PostMapping
    public User saveOne(){
        return new User();
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id){
        return ResponseEntity.ok(new User());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        return ResponseEntity.noContent().build();
    }
}
