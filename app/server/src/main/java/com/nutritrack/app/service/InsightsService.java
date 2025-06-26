package com.nutritrack.app.service;

import com.nutritrack.app.dto.MentalHealthInsightsDTO;
import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.dto.WeightInsightsDTO;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.service.insights.MentalHealthService;
import com.nutritrack.app.service.insights.SleepInsightsService;
import com.nutritrack.app.service.insights.WeightLossService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class InsightsService {
    private SleepInsightsService sleepInsightsService;
    private WeightLossService weightLossService;
    private MentalHealthService mentalHealthService;

    public InsightsService(SleepInsightsService sleepInsightsService, WeightLossService weightLossService, MentalHealthService mentalHealthService) {
        this.sleepInsightsService = sleepInsightsService;
        this.weightLossService = weightLossService;
        this.mentalHealthService = mentalHealthService;
    }

    @Cacheable(value="sleepInsights", key = "#meal.id")
    public SleepInsightsDTO getSleepInsights(Meal meal) {
        return sleepInsightsService.getSleepInsights(meal);
    }

    @Cacheable(value = "weightInsights", key = "#meal.id")
    public WeightInsightsDTO getWeightInsights(Meal meal) {
        return weightLossService.getWeightLossInsights(meal);
    }

    @Cacheable(value = "mentalHealthInsights", key = "#meal.id")
    public MentalHealthInsightsDTO getMentalHealthInsights(Meal meal) {
        return mentalHealthService.getMentalHealthInsights(meal);
    }
}
