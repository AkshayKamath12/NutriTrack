package com.nutritrack.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nutritrack.app.entity.NutriLabel;

import java.util.List;

public interface NutriLabelRepository extends JpaRepository<NutriLabel, Long> {
    List<NutriLabel> findByUserUsername(String username);
}
