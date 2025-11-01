package com.calebpitan.logistics.auth;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CredentialRegistrationRequest(
    @Nullable String firstName,
    @Nullable String lastName,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotBlank @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$") String username) {}
;
