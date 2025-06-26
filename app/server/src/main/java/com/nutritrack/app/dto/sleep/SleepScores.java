package com.nutritrack.app.dto.sleep;

import lombok.Data;

import java.io.Serializable;

@Data
public class SleepScores {
    private double averageAddedSugarsScore;
    private double averageSodiumScore;
    private double averageDietaryFiberScore;
    private double averageProteinScore;
}
