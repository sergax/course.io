package com.sergax.courseio.service;

import com.sergax.courseio.entity.User;

public interface UserService extends BaseService<User, String> {
    boolean isUserExistsByEmail(String email);
}
