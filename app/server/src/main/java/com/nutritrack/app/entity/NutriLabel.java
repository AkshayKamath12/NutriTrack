package com.nutritrack.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String productName;
    private String brandName;
    private String servingSize;
    private Float servings;
    private Integer calories;
    private Float totalFat;
    private Float saturatedFat;
    private Float transFat;
    private Float cholesterol;
    private Float sodium;
    private Float totalCarbohydrates;
    private Float dietaryFiber;
    private Float totalSugars;
    private Float addedSugars;
    private Float protein;
    private Float vitaminD;
    private Float calcium;
    private Float iron;
    private Float potassium;
    private String ingredients;
    private String barcode;

    public NutriLabel(String productName) {
        this.productName = productName;
    }
}
