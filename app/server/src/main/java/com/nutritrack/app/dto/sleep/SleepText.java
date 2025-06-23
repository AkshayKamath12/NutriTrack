package com.nutritrack.app.dto.sleep;

import lombok.Data;

import java.util.List;

@Data
public class SleepText {
    //negative
    private String sugarIntake;
    private String sodiumIntake;
    private String proteinIntake;
    //private List<String> brandInsights;  to be added

    //positive
    private String fiberIntake;

    //private String nutrientDiversity;
}
