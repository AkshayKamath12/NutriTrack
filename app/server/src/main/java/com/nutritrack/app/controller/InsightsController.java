package com.nutritrack.app.controller;

import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.dto.WeightInsightsDTO;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.service.InsightsService;
import com.nutritrack.app.service.MealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/insights")
public class InsightsController {
    private InsightsService insightsService;
    private MealService mealService;

    public InsightsController(InsightsService insightsService, MealService mealService) {
        this.insightsService = insightsService;
        this.mealService = mealService;
    }

    @GetMapping("/sleep/{mealId}")
    public SleepInsightsDTO sleepInsights(@PathVariable long mealId) {
        Meal meal = mealService.getMeal(mealId);
        if (meal == null) {
            return null;
        }
        return insightsService.getSleepInsights(meal);
    }

    @GetMapping("/weightLoss/{mealId}")
    public WeightInsightsDTO weightInsights(@PathVariable long mealId) {
        Meal meal = mealService.getMeal(mealId);
        if (meal == null) {
            return null;
        }
        return insightsService.getWeightInsights(meal);
    }
}
