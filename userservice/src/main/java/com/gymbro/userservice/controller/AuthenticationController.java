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
        if (userService.getUserByEmail(registrationDto.email()).isEmpty() || userService.getUserByUsername(registrationDto.username()).isEmpty()){ // Need to move this logic to the user service
            User user = User.builder()
                    .firstName(registrationDto.firstName())
                    .lastName(registrationDto.lastName())
                    .username(registrationDto.username())
                    .email(registrationDto.email())
                    .password(passwordEncoder.encode(registrationDto.password())) // Encrypt the password
                    .enabled(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .createdAt(java.time.Instant.now())
                    .updatedAt(java.time.Instant.now())
                    .build();

            userService.saveUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or email already exist");
        }
    }

}
