package com.calebpitan.logistics.utils.security;

import com.calebpitan.logistics.user.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class JWTAuthTokenFilter extends OncePerRequestFilter {
  private static final String AUTH_SCHEME = "Bearer ";

  @Autowired private JWTHelper jwtHelper;
  @Autowired private UserDetailsServiceImpl userDetailsService;

  private Optional<String> parseJwt(HttpServletRequest request) {
    final String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith(AUTH_SCHEME)) {
      return Optional.empty();
    }

    return Optional.of(authorizationHeader.substring(AUTH_SCHEME.length()));
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    try {
      this.parseJwt(request)
          .ifPresent(
              (token) -> {
                if (!this.jwtHelper.isValidToken(token)) return;

                final String userIdentifier = this.jwtHelper.getUserFromToken(token);
                final UserDetails userDetails =
                    userDetailsService.loadUserByUsername(userIdentifier);

                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
              });
    } catch (Exception e) {
      log.error("Cannot set user authentication: {}", e.getMessage());
    }

    filterChain.doFilter(request, response);
  }
}
