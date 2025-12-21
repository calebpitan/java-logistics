package com.calebpitan.logistics.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    String password =
        user.getAccountPassword()
            .orElseThrow(() -> new UsernameNotFoundException("Cannot authenticate with password"));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(password)
        .authorities(user.getAuthorities())
        .build();
  }

  public UserDetails loadUserByUsername(Long id) throws UsernameNotFoundException {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    String password =
        user.getAccountPassword()
            .orElseThrow(() -> new UsernameNotFoundException("Cannot authenticate with password"));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(password)
        .authorities(user.getAuthorities())
        .build();
  }
}
