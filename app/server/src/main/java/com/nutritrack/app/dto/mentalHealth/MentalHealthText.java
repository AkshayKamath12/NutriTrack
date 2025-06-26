package com.nutritrack.app.dto.mentalHealth;

import lombok.Data;

import java.io.Serializable;

@Data
public class MentalHealthText implements Serializable {
    private String omegaImbalanceInsight;
    private String totalAddedSugarInsight;
    private String totalDietaryFiberInsight;
    private String tryptophanProxyInsight;
    private String sodiumHydrationBalanceInsight;
    private String magnesiumProxyInsight;
}
