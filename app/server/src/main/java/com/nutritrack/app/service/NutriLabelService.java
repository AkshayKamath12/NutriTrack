package com.nutritrack.app.service;

import com.nutritrack.app.dao.MealRepository;
import com.nutritrack.app.dao.NutriLabelRepository;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutriLabelService {
    private final MealRepository mealRepository;
    private NutriLabelRepository nutriLabelRepository;

    public NutriLabelService(NutriLabelRepository nutriLabelRepository, MealRepository mealRepository) {
        this.nutriLabelRepository = nutriLabelRepository;
        this.mealRepository = mealRepository;
    }

    public List<NutriLabel> getAllNutriLabels(Meal meal) {
        return meal.getNutritionLabels();
    }

    @CacheEvict(value = {"sleepInsights", "weightInsights", "mentalHealthInsights"}, key = "#meal.id")
    public void addNutriLabel(NutriLabel nutriLabel, Meal meal) {
        nutriLabel.setMeal(meal);
        nutriLabelRepository.save(nutriLabel);
        meal.getNutritionLabels().add(nutriLabel);
        mealRepository.save(meal);
    }

    @CacheEvict(value = {"sleepInsights", "weightInsights", "mentalHealthInsights"}, key = "#result", condition = "#result != null")
    public long updateNutriLabel(NutriLabel updatedLabel, long id) {
        Optional<NutriLabel> nutriLabelDB = nutriLabelRepository.findById(id);
        if(nutriLabelDB.isEmpty()) {
            return -1;
        }
        NutriLabel existing = nutriLabelDB.get();
        if (updatedLabel.getBarcode() != null) existing.setBarcode(updatedLabel.getBarcode());
        if (updatedLabel.getBrandName() != null) existing.setBrandName(updatedLabel.getBrandName());
        if (updatedLabel.getProductName() != null) existing.setProductName(updatedLabel.getProductName());
        if (updatedLabel.getServingSize() != null) existing.setServingSize(updatedLabel.getServingSize());
        if (updatedLabel.getIngredients() != null) existing.setIngredients(updatedLabel.getIngredients());
        if (updatedLabel.getCalories() != null) existing.setCalories(updatedLabel.getCalories());
        if (updatedLabel.getTotalFat() != null) existing.setTotalFat(updatedLabel.getTotalFat());
        if (updatedLabel.getSaturatedFat() != null) existing.setSaturatedFat(updatedLabel.getSaturatedFat());
        if (updatedLabel.getTransFat() != null) existing.setTransFat(updatedLabel.getTransFat());
        if (updatedLabel.getCholesterol() != null) existing.setCholesterol(updatedLabel.getCholesterol());
        if (updatedLabel.getSodium() != null) existing.setSodium(updatedLabel.getSodium());
        if (updatedLabel.getTotalCarbohydrates() != null) existing.setTotalCarbohydrates(updatedLabel.getTotalCarbohydrates());
        if (updatedLabel.getDietaryFiber() != null) existing.setDietaryFiber(updatedLabel.getDietaryFiber());
        if (updatedLabel.getTotalSugars() != null) existing.setTotalSugars(updatedLabel.getTotalSugars());
        if (updatedLabel.getAddedSugars() != null) existing.setAddedSugars(updatedLabel.getAddedSugars());
        if (updatedLabel.getProtein() != null) existing.setProtein(updatedLabel.getProtein());
        if (updatedLabel.getVitaminD() != null) existing.setVitaminD(updatedLabel.getVitaminD());
        if (updatedLabel.getCalcium() != null) existing.setCalcium(updatedLabel.getCalcium());
        if (updatedLabel.getIron() != null) existing.setIron(updatedLabel.getIron());
        if (updatedLabel.getPotassium() != null) existing.setPotassium(updatedLabel.getPotassium());
        if (updatedLabel.getServings() != null) existing.setServings(updatedLabel.getServings());
        nutriLabelRepository.save(existing);
        return existing.getMeal().getId();
    }

    @CacheEvict(value = {"sleepInsights", "weightInsights", "mentalHealthInsights"}, key = "#result", condition = "#result != null")
    public long deleteNutriLabel(long id) {
        Optional<NutriLabel> nutriLabelDB = nutriLabelRepository.findById(id);
        if(nutriLabelDB.isEmpty()) {
            return -1;
        }
        NutriLabel nutriLabel = nutriLabelDB.get();
        long mealId = nutriLabel.getMeal().getId();
        nutriLabelRepository.delete(nutriLabel);
        return mealId;
    }
}
