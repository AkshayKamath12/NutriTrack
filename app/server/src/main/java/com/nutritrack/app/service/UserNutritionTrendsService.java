package com.nutritrack.app.service;

import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.entity.NutriLabel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserNutritionTrendsService {
    private MealService mealService;

    public UserNutritionTrendsService(MealService mealService) {
        this.mealService = mealService;
    }

    public List<Map<String, Object>> lastWeekTrends(String username, List<String> nutritionLabelFields, int days) {
        List<Meal> meals = mealService.getAllMealsAfterDate(username, LocalDateTime.now().minusDays(days));
        List<String> validFields = validateNutritionLabels(nutritionLabelFields);
        Map<LocalDate, List<Meal>> mealsGroupedByDate = groupMealsByDate(meals);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Meal>> entry : mealsGroupedByDate.entrySet()) {
            List<Meal> mealsOnDate = entry.getValue();
            List<NutriLabel> nutriLabelList = mealsOnDate.stream().flatMap(meal -> meal.getNutritionLabels().stream()).toList();
            Map<String, Object> resultElement = new HashMap<>();
            resultElement.put("date", entry.getKey());
            calculateTotals(resultElement, nutriLabelList, validFields, mealsOnDate.size());
            result.add(resultElement);
        }
        return result;
    }

    private void calculateTotals(Map<String, Object> resultMap, List<NutriLabel> nutriLabelList, List<String> validFields, int numberOfMeals){
        Map<String, Double> totals = new HashMap<>();

        for (String field : validFields) {
            totals.put(field, 0.0);
        }

        for (NutriLabel nutriLabel : nutriLabelList) {
            for (String field : validFields) {
                try {
                    Field fieldLabel = nutriLabel.getClass().getDeclaredField(field);
                    fieldLabel.setAccessible(true);
                    Object value = fieldLabel.get(nutriLabel);
                    if (value instanceof Number) {
                        totals.put(field, totals.get(field) + ((Number) value).doubleValue());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (String key: totals.keySet()) {
            resultMap.put(key, totals.get(key) / numberOfMeals);
        }

    }

    private List<String> validateNutritionLabels(List<String> nutritionLabelFields) {
        Field[] fields = NutriLabel.class.getDeclaredFields();
        Set<String> nutritionLabelFieldSet = new HashSet<>();
        for (Field field : fields) {
            field.setAccessible(true);
            nutritionLabelFieldSet.add(field.getName());
        }
        List<String> validLabels = new ArrayList<>();
        for (String nutritionLabelField : nutritionLabelFields) {
            if (nutritionLabelFieldSet.contains(nutritionLabelField)) {
                validLabels.add(nutritionLabelField);
            }
        }
        return validLabels;
    }

    private Map<LocalDate, List<Meal>> groupMealsByDate(List<Meal> meals) {
        Map<LocalDate, List<Meal>> groupMealsByDate = new HashMap<>();
        for (Meal meal : meals) {
            LocalDate date = meal.getTimestamp().toLocalDate();
            groupMealsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(meal);
        }
        return groupMealsByDate;
    }

}
