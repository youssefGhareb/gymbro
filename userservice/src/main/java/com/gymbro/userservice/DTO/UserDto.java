package com.gymbro.userservice.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record UserDto(@NotNull UUID id, @NotNull String username, @NotNull String email, Instant createdAt) {
}
