package com.calebpitan.logistics.auth;

import jakarta.validation.constraints.Email;

public record MagicLinkRegistrationRequest(@Email String email) {}
