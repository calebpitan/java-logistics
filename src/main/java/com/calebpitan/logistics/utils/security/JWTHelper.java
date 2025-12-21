package com.calebpitan.logistics.utils.security;

import com.calebpitan.logistics.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JWTHelper {
  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration-ms}")
  private int jwtExpirationMs;

  private SecretKey key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(UserDetails user) {
    Date issueDate = new Date();
    Date expirationDate = new Date(issueDate.getTime() + jwtExpirationMs);
    String userIdentifier =
        Optional.ofNullable(user.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("User identifier cannot be null"));

    return Jwts.builder()
        .subject(userIdentifier)
        .issuedAt(issueDate)
        .expiration(expirationDate)
        .signWith(key)
        .compact();
  }

  @Nullable
  public String getUserFromToken(String token) {
      Claims payload = Jwts.parser().verifyWith(this.key).build().parseSignedClaims(token).getPayload();
      String userIdentifier = payload.getSubject();

      return userIdentifier;
  }

  public boolean isValidToken(String token) {
      try {
          Jwts.parser().verifyWith(this.key).build().parseSignedClaims(token);
           return true;
      } catch (Exception e) {
          log.error("Invalid JWT token: {}", e.getMessage());
          return false;
      }
  }
}
