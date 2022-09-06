package com.sergax.course.io.controller;

import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.UserServiceIml;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserServiceIml userServiceIml;

    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userServiceIml.getUser(name));
    }

    @GetMapping()
    public ResponseEntity<Set<User>> getAllUsers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userServiceIml.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(userServiceIml.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userServiceIml.updateUser(user));
    }

}
