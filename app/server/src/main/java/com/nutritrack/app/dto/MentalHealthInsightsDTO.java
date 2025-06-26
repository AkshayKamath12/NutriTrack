package com.nutritrack.app.dto;

import com.nutritrack.app.dto.mentalHealth.MentalHealthNumeric;
import com.nutritrack.app.dto.mentalHealth.MentalHealthScores;
import com.nutritrack.app.dto.mentalHealth.MentalHealthText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentalHealthInsightsDTO{
    private MentalHealthNumeric mentalHealthNumeric;
    private MentalHealthText mentalHealthText;
    private MentalHealthScores mentalHealthScores;
}
