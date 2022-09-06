package com.sergax.course.io.controller;

import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.UserServiceIml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {
    @Mock
    private UserServiceIml userServiceIml;
    @InjectMocks
    private UserRestController userRestController;
    private User userTest;

    @BeforeEach
    void setUp() {
        userTest = User.builder()
                .name("name")
                .email("email")
                .password("123456")
                .build();
    }

    @Test
    void getUser() throws ExecutionException, InterruptedException {
        userRestController.getUser(userTest.getName());
        verify(userServiceIml).getUser(userTest.getName());
    }

    @Test
    void getAllUsers() throws ExecutionException, InterruptedException {
        userRestController.getAllUsers();
        verify(userServiceIml).getAllUsers();
    }

    @Test
    void createUser() throws ExecutionException, InterruptedException {
        userRestController.createUser(userTest);
        verify(userServiceIml).createUser(userTest);
    }

    @Test
    void updateUser() throws ExecutionException, InterruptedException {
        userRestController.updateUser(userTest);
        verify(userServiceIml).updateUser(userTest);
    }
}