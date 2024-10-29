package com.gymbro.userservice.service;

import com.gymbro.userservice.DTO.UserDto;
import com.gymbro.userservice.exceptions.BadUserIdentifer;
import com.gymbro.userservice.exceptions.DeletionException;
import com.gymbro.userservice.mapper.UserMapper;
import com.gymbro.userservice.model.User;
import com.gymbro.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto saveUser(User user) {
        User newUser = userRepository.save(user);

        log.info("Created new user with username: {} and id: {}", newUser.getUsername(), newUser.getId());

        return userMapper.userToUserDto(newUser);
    }

    @Override
    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadUserIdentifer("ID", id.toString()));

        log.debug("Found user: {} with id: {}", user.getUsername(), id);

        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BadUserIdentifer("username", username));

        log.debug("Found user: {}", user.getUsername());

        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BadUserIdentifer("email", email));

        log.debug("Found user: {} with email: {}", user.getUsername(), email);

        return userMapper.userToUserDto(user);
    }

    @Transactional
    @Override
    public boolean deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new BadUserIdentifer("ID", id.toString());
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error during user deletion: {}", e.getMessage());
            throw new DeletionException(String.format("Error during user deletion, user ID: %s", id));
        }

        return true;
    }

}
