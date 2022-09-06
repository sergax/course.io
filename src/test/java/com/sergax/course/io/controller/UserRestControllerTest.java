package com.sergax.course.io.controller;

import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.iml.UserServiceIml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void getUser() {
        userRestController.getUser(userTest.getName());
        verify(userServiceIml).getByName(userTest.getName());
    }

    @Test
    void getAllUsers() {
        userRestController.getAllUsers();
        verify(userServiceIml).getAll();
    }

    @Test
    void createUser() {
        userRestController.createUser(userTest);
        verify(userServiceIml).create(userTest);
    }

    @Test
    void updateUser() {
        userRestController.updateUser(userTest);
        verify(userServiceIml).update(userTest);
    }
}