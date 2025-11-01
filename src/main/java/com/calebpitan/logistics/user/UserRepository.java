package com.calebpitan.logistics.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsernameEquals(String username);
    boolean existsByEmailEquals(String email);

    Optional<User> findByEmail(String email);
}
