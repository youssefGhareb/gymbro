package com.gymbro.userservice.controller;

import com.gymbro.userservice.DTO.UserRegistrationDTO;
import com.gymbro.userservice.model.User;
import com.gymbro.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @NotNull UserRegistrationDTO registrationDto){
        if (userService.getUserByEmail(registrationDto.getEmail()).isEmpty() || userService.getUserByUsername(registrationDto.getUsername()).isEmpty()){ // Need to move this logic to the user service
            User user = new User();
            user.setFirstName(registrationDto.getFirstName());
            user.setLastName(registrationDto.getLastName());
            user.setUsername(registrationDto.getUsername());
            user.setEmail(registrationDto.getEmail());
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword())); // Encrypt the password
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCreatedAt(java.time.Instant.now());
            user.setUpdatedAt(java.time.Instant.now());

            userService.saveUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or email already exist");
        }
    }

}
