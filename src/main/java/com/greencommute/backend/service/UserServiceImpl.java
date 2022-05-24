package com.greencommute.backend.service;

import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpa userJpa;

    @Autowired
    public UserServiceImpl(UserJpa userJpa) {
        this.userJpa = userJpa;
    }
    @Override
    public Optional<User> getUserById(int userId) {
        return userJpa.findById(userId);
    }
}