package com.calebpitan.logistics.auth;

import jakarta.annotation.Nullable;
import lombok.NonNull;

public record AuthenticationResult(@NonNull String accessToken, @Nullable String refreshToken) {}
