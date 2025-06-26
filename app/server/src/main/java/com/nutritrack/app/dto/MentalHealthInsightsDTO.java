package com.nutritrack.app.dto;

import com.nutritrack.app.dto.mentalHealth.MentalHealthNumeric;
import com.nutritrack.app.dto.mentalHealth.MentalHealthScores;
import com.nutritrack.app.dto.mentalHealth.MentalHealthText;
import lombok.Data;

import java.io.Serializable;

@Data
public class MentalHealthInsightsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private MentalHealthNumeric mentalHealthNumeric;
    private MentalHealthText mentalHealthText;
    private MentalHealthScores mentalHealthScores;
}
