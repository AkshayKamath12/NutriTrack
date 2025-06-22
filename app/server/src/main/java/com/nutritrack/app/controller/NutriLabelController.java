package com.nutritrack.app.controller;

import com.nutritrack.app.entity.NutriLabel;
import com.nutritrack.app.service.NutriLabelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
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
            return new ArrayList<>();
        }
        return nutriLabelService.getAllNutriLabels(username);
    }

    @PostMapping("/labels")
    public ResponseEntity<?> addNutriLabel(@RequestBody NutriLabel nutriLabel, Principal principal) {
        String username = principal.getName();
        if(username == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        nutriLabelService.addNutriLabel(nutriLabel, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/labels/{id}")
    public ResponseEntity<?> updateNutriLabel(@RequestBody NutriLabel nutriLabel, @PathVariable long id) {
        boolean addedLabel = nutriLabelService.updateNutriLabel(nutriLabel, id);
        if(!addedLabel) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity<?> deleteNutriLabel(@PathVariable long id) {
        boolean removedLabel = nutriLabelService.deleteNutriLabel(id);
        if(!removedLabel) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
