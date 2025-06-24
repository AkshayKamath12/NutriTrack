package com.nutritrack.app.dto.weight;

import lombok.Data;

@Data
public class WeightNumeric {
    private double totalCalories;
    private double averageCalories;
    private double totalAddedSugars;
    private boolean exceedSodiumCarbLimit; //totalSodium > 800mg and totalCarbs > 50g
    private double satietyScore; //2 × totalFiber + 1.5 × totalProtein − 0.5 × totalAddedSugar − 0.2 × totalFat
}
