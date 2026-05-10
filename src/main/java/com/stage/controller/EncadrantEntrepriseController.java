package com.stage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stage.model.Encadrant_Entreprise;
import com.stage.service.EncadrantEntrepriseService;

@RestController
@RequestMapping("/encadrants-entreprises")
public class EncadrantEntrepriseController {

    private final EncadrantEntrepriseService service;

    public EncadrantEntrepriseController(EncadrantEntrepriseService service) {
        this.service = service;
    }

    @PostMapping
    public Encadrant_Entreprise createEncadrantEntreprise(@RequestBody Encadrant_Entreprise encadrant) {
        return service.saveEncadrantEntreprise(encadrant);
    }

    @PutMapping
    public Encadrant_Entreprise updateEncadrantEntreprise(@RequestBody Encadrant_Entreprise encadrant) {
        return service.updateEncadrantEntreprise(encadrant);
    }

    @DeleteMapping("/{id}")
    public void deleteEncadrantEntreprise(@PathVariable Long id) {
        service.deleteEncadrantEntreprise(id);
    }

    @GetMapping("/{id}")
    public Encadrant_Entreprise getEncadrantEntrepriseById(@PathVariable Long id) {
        return service.getEncadrantById(id);
    }

    @GetMapping("/nom/{nom}")
    public Encadrant_Entreprise getEncadrantEntrepriseByNom(@PathVariable String nom) {
        return service.getEncadrantByNom(nom);
    }

    @GetMapping
    public List<Encadrant_Entreprise> getAllEncadrantsEntreprises() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Encadrant_Entreprise> searchEncadrantsEntreprises(@RequestParam(required = false) String searchTerm) {
        return service.searchEncadrants(searchTerm);
    }
}

