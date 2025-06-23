package com.nutritrack.app.controller;

import com.nutritrack.app.dto.SleepInsightsDTO;
import com.nutritrack.app.service.InsightsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/insights")
public class InsightsController {
    private InsightsService insightsService;

    public InsightsController(InsightsService insightsService) {
        this.insightsService = insightsService;
    }

    @GetMapping("/sleep")
    public SleepInsightsDTO sleepInsights(Principal principal) {
        String username = principal.getName();
        return insightsService.getSleepInsights(username);
    }
}
