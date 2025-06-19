package com.nutritrack.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nutriLabels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NutriLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private String brandName;
    private String servingSize;
    private float servings;
    private int calories;
    private float totalFat;
    private float saturatedFat;
    private float transFat;
    private float cholesterol;
    private float sodium;
    private float totalCarbohydrates;
    private float dietaryFiber;
    private float totalSugars;
    private float addedSugars;
    private float protein;
    private float vitaminD;
    private float calcium;
    private float iron;
    private float potassium;
    private String ingredients;
    private String barcode;

    public NutriLabel(String productName) {
        this.productName = productName;
    }
}
