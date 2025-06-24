package com.nutritrack.app.dto;


import com.nutritrack.app.dto.weight.WeightNumeric;
import com.nutritrack.app.dto.weight.WeightText;
import lombok.Data;

@Data
public class WeightInsightsDTO {
    private WeightText weightText;
    private WeightNumeric weightNumeric;
    //private String improvementStrategy; might use openAI for this
}
