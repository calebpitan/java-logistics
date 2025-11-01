package com.calebpitan.logistics.auth;

import com.calebpitan.logistics.account.Account;
import com.calebpitan.logistics.account.AccountRepository;
import com.calebpitan.logistics.user.User;
import com.calebpitan.logistics.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  private boolean isUsernameAvailable(String username) {
    return !userRepository.existsByUsernameEquals(username);
  }

  private boolean isEmailAvailable(String email) {
    return !userRepository.existsByEmailEquals(email);
  }

  @Transactional
  public void register(CredentialRegistrationRequest request) {
    if (!isUsernameAvailable(request.username())) {
      throw new IllegalArgumentException("Username is already taken");
    }

    if (!isEmailAvailable(request.email())) {
      throw new IllegalArgumentException("Email is already taken");
    }

    User user =
        User.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .username(request.username())
            .build();
    String encodedPassword = passwordEncoder.encode(request.password());
    Account account = Account.builder().password(encodedPassword).user(user).build();

    userRepository.save(user);
    accountRepository.save(account);
  }

  @Transactional
  public void register(MagicLinkRegistrationRequest request) {
    if (!isEmailAvailable(request.email())) {
      throw new IllegalArgumentException("Email is already taken");
    }

    User user = User.builder().email(request.email()).build();
    Account account = Account.builder().user(user).build();

    userRepository.save(user);
    accountRepository.save(account);
  }

  public Authentication login(CredentialsLoginRequest request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.identifier(), request.password()));
      User user = (User) authentication.getPrincipal();
      return authentication;
    } catch (AuthenticationException e) {
      log.error("Error while authenticating user", e);
      throw e;
    }
  }
}
