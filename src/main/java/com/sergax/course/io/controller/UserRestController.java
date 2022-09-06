package com.sergax.course.io.controller;

import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.iml.UserServiceIml;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserServiceIml userServiceIml;

    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) {
        return ResponseEntity.ok(userServiceIml.getByName(name));
    }

    @GetMapping()
    public ResponseEntity<Set<User>> getAllUsers() {
        return ResponseEntity.ok(userServiceIml.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceIml.create(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userServiceIml.update(user));
    }

}
