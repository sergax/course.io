package com.sergax.course.io.controller;

import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.UserServiceIml;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserServiceIml userServiceIml;

    @GetMapping("/{name}")
    public User getUser(@PathVariable String name) throws ExecutionException, InterruptedException {
        return userServiceIml.getUser(name);
    }

    @GetMapping()
    public Set<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userServiceIml.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userServiceIml.createUser(user);
    }

    @PutMapping
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userServiceIml.updateUser(user);
    }
}
