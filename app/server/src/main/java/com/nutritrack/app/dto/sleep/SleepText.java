package com.nutritrack.app.dto.sleep;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SleepText implements Serializable {
    private static final long serialVersionUID = 1L;
    private String averageAddedSugarsInsight;
    private String averageSodiumInsight;
    private String averageDietaryFiberInsight;
    private String averageProteinInsight;
}
