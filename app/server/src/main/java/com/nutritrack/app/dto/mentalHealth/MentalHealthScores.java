package com.nutritrack.app.dto.mentalHealth;

import lombok.Data;

@Data
public class MentalHealthScores {
    private double omegaImbalanceScore;
    private double totalAddedSugarScore;
    private double totalDietaryFiberScore;
    private double tryptophanProxyScore;
    private double sodiumHydrationBalanceScore;
    private double magnesiumProxyScore;
}
