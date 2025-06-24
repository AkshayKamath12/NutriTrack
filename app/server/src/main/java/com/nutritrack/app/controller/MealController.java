package com.nutritrack.app.controller;

import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/allMeals")
    public List<Meal> getMeals(Principal principal) {
        String username = principal.getName();
        if(username == null) {
            return null;
        }
        return mealService.getAllMeals(username);
    }

    @GetMapping("/meal/{mealId}")
    public Meal getMeal(@PathVariable long mealId) {
        return mealService.getMeal(mealId);
    }

    @PostMapping("/meal")
    public ResponseEntity<?> addMeal(@RequestBody Meal meal, Principal principal) {
        String username = principal.getName();
        if(username == null) {
            return new ResponseEntity<>("Username is null", HttpStatus.BAD_REQUEST);
        }
        mealService.addMeal(username, meal);
        return new ResponseEntity<>(meal, HttpStatus.CREATED);
    }

    @PutMapping("/meal/{mealId}")
    public ResponseEntity<?> updateMeal(@PathVariable long mealId, @RequestBody Meal meal) {
        mealService.updateMeal(mealId, meal);
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }

    @DeleteMapping("/meal/{mealId}")
    public ResponseEntity<?> deleteMeal(@PathVariable long mealId) {
        mealService.deleteMeal(mealId);
        return new ResponseEntity<>(mealId, HttpStatus.OK);
    }
}
