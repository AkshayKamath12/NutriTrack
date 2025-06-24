package com.nutritrack.app.service;

import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.dto.WeightInsightsDTO;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.service.insights.SleepInsightsService;
import com.nutritrack.app.service.insights.WeightLossService;
import org.springframework.stereotype.Service;


@Service
public class InsightsService {
    private SleepInsightsService sleepInsightsService;
    private WeightLossService weightLossService;

    public InsightsService(SleepInsightsService sleepInsightsService, WeightLossService weightLossService) {
        this.sleepInsightsService = sleepInsightsService;
        this.weightLossService = weightLossService;
    }

    public SleepInsightsDTO getSleepInsights(Meal meal) {
        return sleepInsightsService.getSleepInsights(meal);
    }

    public WeightInsightsDTO getWeightInsights(Meal meal) {
        return weightLossService.getWeightLossInsights(meal);
    }
}
