package com.nutritrack.app.dto.weight;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeightText implements Serializable {
    private static final long serialVersionUID = 1L;
    private String totalCaloriesInsight;
    private String averageCaloriesInsight;
    private String totalAddedSugarsInsight;
    private String exceedSodiumCarbLimitInsight;
    private String satietyScoreInsight;
}
