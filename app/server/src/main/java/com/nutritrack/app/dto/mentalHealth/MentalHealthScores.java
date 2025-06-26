package com.nutritrack.app.dto.mentalHealth;

import lombok.Data;

import java.io.Serializable;

@Data
public class MentalHealthScores implements Serializable {
    private double omegaImbalanceScore;
    private double totalAddedSugarScore;
    private double totalDietaryFiberScore;
    private double tryptophanProxyScore;
    private double sodiumHydrationBalanceScore;
    private double magnesiumProxyScore;
}
