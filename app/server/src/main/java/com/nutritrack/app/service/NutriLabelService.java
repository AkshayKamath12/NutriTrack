package com.nutritrack.app.service;

import com.nutritrack.app.dao.NutriLabelRepository;
import com.nutritrack.app.entity.NutriLabel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutriLabelService {
    private NutriLabelRepository nutriLabelRepository;

    public NutriLabelService(NutriLabelRepository nutriLabelRepository) {
        this.nutriLabelRepository = nutriLabelRepository;
    }

    public List<NutriLabel> getAllNutriLabels(String username) {
        return nutriLabelRepository.findByUserUsername(username);
    }
}
