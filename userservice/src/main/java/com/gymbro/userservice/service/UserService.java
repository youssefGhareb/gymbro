package com.gymbro.userservice.service;

import com.gymbro.userservice.DTO.UserDto;
import com.gymbro.userservice.model.User;

import java.util.UUID;

public interface UserService {
    UserDto saveUser(User user);

    UserDto getUserById(UUID id);

    UserDto getUserByUsername(String username);

    UserDto getUserByEmail(String email);

    boolean deleteUser(UUID id);
}
