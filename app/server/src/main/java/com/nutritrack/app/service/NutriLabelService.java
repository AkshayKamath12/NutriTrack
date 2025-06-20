package com.nutritrack.app.service;

import com.nutritrack.app.dao.NutriLabelRepository;
import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutriLabelService {
    private NutriLabelRepository nutriLabelRepository;
    private UserService userService;

    public NutriLabelService(NutriLabelRepository nutriLabelRepository, UserService userService) {
        this.nutriLabelRepository = nutriLabelRepository;
        this.userService = userService;
    }

    public List<NutriLabel> getAllNutriLabels(String username) {
        return nutriLabelRepository.findByUserUsername(username);
    }

    public void addNutriLabel(NutriLabel nutriLabel, String username) {
        Optional<User> user= userService.findByUsername(username);
        if(!user.isPresent()) {
            return;
        }
        User nutriLabelUser = user.get();
        nutriLabel.setUser(nutriLabelUser);
        nutriLabelRepository.save(nutriLabel);
    }
}
