package com.nutritrack.app.controller;

import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.service.MealService;
import com.nutritrack.app.service.NutriLabelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/nutri")
public class NutriLabelController {
    private NutriLabelService nutriLabelService;
    private MealService mealService;

    public NutriLabelController(NutriLabelService nutriLabelService, MealService mealService) {
        this.nutriLabelService = nutriLabelService;
        this.mealService = mealService;
    }

    @GetMapping("/labels/{mealId}")
    public List<NutriLabel> getNutriLabels(@PathVariable long mealId,Principal principal) {
        String username = principal.getName();
        if(username == null) {
            return null;
        }
        Meal meal = mealService.getMeal(mealId);
        if (meal == null) {
            return null;
        }
        return nutriLabelService.getAllNutriLabels(meal);
    }

    @PostMapping("/labels/{mealId}")
    public ResponseEntity<?> addNutriLabel(@RequestBody NutriLabel nutriLabel, @PathVariable long mealId, Principal principal) {
        String username = principal.getName();
        if(username == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Meal meal = mealService.getMeal(mealId);
        if (meal == null) {
            return null;
        }
        nutriLabelService.addNutriLabel(nutriLabel, meal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/labels/{id}")
    public ResponseEntity<?> updateNutriLabel(@RequestBody NutriLabel nutriLabel, @PathVariable long id) {
        boolean addedLabel = nutriLabelService.updateNutriLabel(nutriLabel, id);
        if(!addedLabel) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity<?> deleteNutriLabel(@PathVariable long id) {
        boolean removedLabel = nutriLabelService.deleteNutriLabel(id);
        if(!removedLabel) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
