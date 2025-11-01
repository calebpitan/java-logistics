package com.calebpitan.logistics.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
  private AuthService authService;

  @PostMapping("/credentials/register")
  public ResponseEntity<Void> register(@Validated @RequestBody CredentialRegistrationRequest request) {
    log.info("Credential registration for user received {}", request);
    authService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/credentials/login")
  public ResponseEntity<Void> login(@Validated @RequestBody CredentialsLoginRequest request) {
      log.info("Credentials login for user received {}", request);
      var result = authService.login(request);
      log.info("Login result {}", result);
      return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/magic-link/register")
  public ResponseEntity<Void> register(@Validated @RequestBody MagicLinkRegistrationRequest request) {
      log.info("Magic link registration for user received {}", request);
      authService.register(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
