package com.nutritrack.app.dao;

import com.nutritrack.app.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserUsername(String username);
    List<Meal> findByUserUsernameAndTimestampAfter(String username, LocalDateTime timestamp);

}
