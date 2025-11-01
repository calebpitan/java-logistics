package com.calebpitan.logistics.user;

import com.calebpitan.logistics.account.Account;
import com.calebpitan.logistics.company.Company;
import com.calebpitan.logistics.utils.entity.AbstractAuditable;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbstractAuditable<User, Long> {
  @Nullable private String firstName;
  @Nullable private String lastName;

  @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]*$")
  @Column(unique = true, nullable = false)
  @NotBlank
  private String username;

  @Email @NotBlank private String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Account> accounts = new ArrayList<>();

  @OneToOne
  private Company company;

  public Optional<String> getAccountPassword() {
    return accounts.stream()
        .filter(acc -> acc.getPassword() != null)
        .findFirst()
        .map(Account::getPassword);
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }
}
