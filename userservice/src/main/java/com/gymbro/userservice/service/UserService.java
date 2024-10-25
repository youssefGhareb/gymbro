package com.gymbro.userservice.service;

import com.gymbro.userservice.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    void deleteUser(UUID id);
}
