package com.nutritrack.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nutritrack.app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
