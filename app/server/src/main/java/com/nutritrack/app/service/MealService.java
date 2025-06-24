package com.nutritrack.app.service;

import com.nutritrack.app.dao.MealRepository;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private MealRepository mealRepository;
    private UserService userService;

    public MealService(MealRepository mealRepository, UserService userService) {
        this.mealRepository = mealRepository;
        this.userService = userService;
    }

    public List<Meal> getAllMeals(String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return mealRepository.findByUserUsername(username);
    }

    public Meal getMeal(Long mealId) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        if (meal.isEmpty()) {
            return null;
        }
        return meal.get();
    }

    public void addMeal(String username, Meal meal) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty()) {
            return;
        }
        User userObj = user.get();
        meal.setTimestamp(LocalDateTime.now());
        meal.setNutritionLabels(new ArrayList<>());
        meal.setUser(userObj);
        mealRepository.save(meal);
    }

    public boolean deleteMeal(Long mealId) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        if (meal.isEmpty()) {
            return false;
        }
        mealRepository.delete(meal.get());
        return true;
    }

    public boolean updateMeal(Long mealId, Meal inpMeal) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        if (meal.isEmpty()) {
            return false;
        }
        Meal oldMeal = meal.get();
        oldMeal.setTimestamp(LocalDateTime.now());
        oldMeal.setNutritionLabels(inpMeal.getNutritionLabels());
        mealRepository.save(oldMeal);
        return true;
    }

}
