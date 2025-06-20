package com.nutritrack.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.nutritrack.app.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
