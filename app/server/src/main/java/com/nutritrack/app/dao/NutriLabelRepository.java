package com.nutritrack.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nutritrack.app.entity.NutriLabel;

public interface NutriLabelRepository extends JpaRepository<NutriLabel, Long> {
}
