package com.nutritrack.app.service;

import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.entity.Meal;
import com.nutritrack.app.service.insights.SleepInsightsService;
import org.springframework.stereotype.Service;


@Service
public class InsightsService {
    private SleepInsightsService sleepInsightsService;

    public InsightsService(SleepInsightsService sleepInsightsService) {
        this.sleepInsightsService = sleepInsightsService;
    }

    public SleepInsightsDTO getSleepInsights(Meal meal) {
        return sleepInsightsService.getSleepInsights(meal);
    }
}
