package com.nutritrack.app.dto.weight;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeightText {
    private String totalCaloriesInsight;
    private String averageCaloriesInsight;
    private String totalAddedSugarsInsight;
    private String exceedSodiumCarbLimitInsight;
    private String satietyScoreInsight;
}
