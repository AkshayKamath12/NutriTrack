package com.nutritrack.app.controller;

import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.service.NutriLabelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/nutri")
public class NutriLabelController {
    private NutriLabelService nutriLabelService;

    public NutriLabelController(NutriLabelService nutriLabelService) {
        this.nutriLabelService = nutriLabelService;
    }

    @GetMapping("/labels")
    public List<NutriLabel> getNutriLabels(Principal principal) {
        String username = principal.getName();
        if(username == null) {
            return null;
        }
        return nutriLabelService.getAllNutriLabels(username);
    }
}
