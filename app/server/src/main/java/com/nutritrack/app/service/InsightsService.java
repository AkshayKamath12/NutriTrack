package com.nutritrack.app.service;

import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.dto.sleep.SleepNumeric;
import com.nutritrack.app.dto.sleep.SleepText;
import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.service.insights.SleepInsightsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsightsService {
    private SleepInsightsService sleepInsightsService;

    public InsightsService(SleepInsightsService sleepInsightsService) {
        this.sleepInsightsService = sleepInsightsService;
    }

    public SleepInsightsDTO getSleepInsights(String username) {
        return sleepInsightsService.getSleepInsights(username);
    }
}
