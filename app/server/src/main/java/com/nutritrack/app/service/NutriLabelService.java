package com.nutritrack.app.service;

import com.nutritrack.app.dao.NutriLabelRepository;
import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutriLabelService {
    private NutriLabelRepository nutriLabelRepository;
    private UserService userService;

    public NutriLabelService(NutriLabelRepository nutriLabelRepository, UserService userService) {
        this.nutriLabelRepository = nutriLabelRepository;
        this.userService = userService;
    }

    public List<NutriLabel> getAllNutriLabels(String username) {
        return nutriLabelRepository.findByUserUsername(username);
    }

    public void addNutriLabel(NutriLabel nutriLabel, String username) {
        Optional<User> user= userService.findByUsername(username);
        if(user.isEmpty()) {
            return;
        }
        User nutriLabelUser = user.get();
        nutriLabel.setUser(nutriLabelUser);
        nutriLabelRepository.save(nutriLabel);
    }

    public boolean updateNutriLabel(NutriLabel updatedLabel, long id) {
        Optional<NutriLabel> nutriLabelDB = nutriLabelRepository.findById(id);
        if(nutriLabelDB.isEmpty()) {
            return false;
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
        return true;
    }

    public boolean deleteNutriLabel(long id) {
        Optional<NutriLabel> nutriLabelDB = nutriLabelRepository.findById(id);
        if(nutriLabelDB.isEmpty()) {
            return false;
        }
        NutriLabel nutriLabel = nutriLabelDB.get();
        nutriLabelRepository.delete(nutriLabel);
        return true;
    }
}
