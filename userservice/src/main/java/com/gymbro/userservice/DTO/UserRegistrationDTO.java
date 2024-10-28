package com.gymbro.userservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record UserRegistrationDTO(String firstName, String lastName, String username, String password, String email){}
