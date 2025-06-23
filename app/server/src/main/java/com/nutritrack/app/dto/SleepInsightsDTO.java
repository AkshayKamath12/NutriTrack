package com.nutritrack.app.dto;

import com.nutritrack.app.dto.sleep.SleepNumeric;
import com.nutritrack.app.dto.sleep.SleepText;
import lombok.Data;

@Data
public class SleepInsightsDTO {
    private SleepText sleepText;
    private SleepNumeric sleepNumeric;
    //private String improvementStrategy; might use openAI for this
}
