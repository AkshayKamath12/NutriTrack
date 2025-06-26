package com.nutritrack.app.dto.sleep;

import lombok.Data;

import java.io.Serializable;

@Data
public class SleepNumeric implements Serializable {
    private static final long serialVersionUID = 1L;
    private double averageAddedSugars;
    private double averageSodium;
    private double averageDietaryFiber;
    private double averageProtein;
}
