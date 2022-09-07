package com.sergax.courseio.controller;

import com.sergax.courseio.entity.User;
import com.sergax.courseio.service.UserService;
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
    private UserService userService;
    @InjectMocks
    private UserRestController userRestController;
    private User userTest;

    @BeforeEach
    void setUp() {
        userTest = User.builder()
                .id("123456")
                .name("name")
                .email("email")
                .password("123456")
                .build();
    }

    @Test
    void getUser() {
        userRestController.getUserById(userTest.getId());
        verify(userService).getById(userTest.getId());
    }

    @Test
    void getAllUsers() {
        userRestController.getAllUsers();
        verify(userService).getAll();
    }

    @Test
    void createUser() {
        userRestController.createUser(userTest);
        verify(userService).create(userTest);
    }

    @Test
    void updateUser() {
        userRestController.updateUser(userTest.getId(), userTest);
        verify(userService).update(userTest.getId(), userTest);
    }
}