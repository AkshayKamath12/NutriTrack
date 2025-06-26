package com.nutritrack.app.dto.sleep;

import lombok.Data;

import java.io.Serializable;

@Data
public class SleepScores implements Serializable {
    private static final long serialVersionUID = 1L;
    private double averageAddedSugarsScore;
    private double averageSodiumScore;
    private double averageDietaryFiberScore;
    private double averageProteinScore;
}
