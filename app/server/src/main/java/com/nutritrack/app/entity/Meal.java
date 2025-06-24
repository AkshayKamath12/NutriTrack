package com.nutritrack.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NutriLabel> nutritionLabels = new ArrayList<>();
}
