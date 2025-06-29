package com.nutritrack.app.controller;

import com.nutritrack.app.service.UserNutritionTrendsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userInsights")
public class UserNutritionTrendsController {
    private UserNutritionTrendsService userNutritionTrendsService;

    public UserNutritionTrendsController(UserNutritionTrendsService userNutritionTrendsService) {
        this.userNutritionTrendsService = userNutritionTrendsService;
    }

    @GetMapping("/lastMonth")
    public List<Map<String, Object>> lastMonthInsights(@RequestParam List<String> fields, @RequestParam(defaultValue = "31") int days, Principal principal) {
        String username = principal.getName();
        if(username == null) {
            throw new RuntimeException("Username is null");
        }
        if(days > 31 || days < 1) {
            throw new RuntimeException("Invalid days");
        }
        return userNutritionTrendsService.lastWeekTrends(username, fields, days);
    }
}
