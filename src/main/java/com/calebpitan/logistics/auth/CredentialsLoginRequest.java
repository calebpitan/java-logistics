package com.calebpitan.logistics.auth;

import jakarta.validation.constraints.Email;

public record CredentialsLoginRequest(@Email String identifier, String password) {}
