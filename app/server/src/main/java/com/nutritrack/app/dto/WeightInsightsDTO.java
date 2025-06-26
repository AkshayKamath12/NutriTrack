package com.nutritrack.app.dto;


import com.nutritrack.app.dto.weight.WeightNumeric;
import com.nutritrack.app.dto.weight.WeightScores;
import com.nutritrack.app.dto.weight.WeightText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeightInsightsDTO {
    private WeightText weightText;
    private WeightNumeric weightNumeric;
    private WeightScores weightScores;
    //private String improvementStrategy; might use openAI for this
}
