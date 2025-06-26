package com.nutritrack.app.dto;

import com.nutritrack.app.dto.sleep.SleepNumeric;
import com.nutritrack.app.dto.sleep.SleepScores;
import com.nutritrack.app.dto.sleep.SleepText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SleepInsightsDTO {
    private SleepText sleepText;
    private SleepNumeric sleepNumeric;
    private SleepScores sleepScores;
    //private String improvementStrategy; might use openAI for this
}
