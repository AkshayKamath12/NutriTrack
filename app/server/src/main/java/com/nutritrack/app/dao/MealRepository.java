package com.nutritrack.app.dao;

import com.nutritrack.app.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    public List<Meal> findByUserUsername(String username);
}
