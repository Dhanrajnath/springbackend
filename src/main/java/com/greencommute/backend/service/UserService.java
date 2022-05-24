package com.greencommute.backend.service;

import com.greencommute.backend.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(int userId);
}
