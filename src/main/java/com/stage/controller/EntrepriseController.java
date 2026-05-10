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

import com.stage.model.Entreprise;
import com.stage.service.EntrepriseService;

@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService service;

    public EntrepriseController(EntrepriseService service) {
        this.service = service;
    }

    @PostMapping
    public Entreprise createEntreprise(@RequestBody Entreprise entreprise) {
        return service.saveEntreprise(entreprise);
    }

    @PutMapping
    public Entreprise updateEntreprise(@RequestBody Entreprise entreprise) {
        return service.updateEntreprise(entreprise);
    }

    @DeleteMapping("/{id}")
    public void deleteEntreprise(@PathVariable Long id) {
        service.deleteEntreprise(id);
    }

    @GetMapping("/{id}")
    public Entreprise getEntrepriseById(@PathVariable Long id) {
        return service.getEntrepriseById(id);
    }

    @GetMapping("/nom/{nom}")
    public Entreprise getEntrepriseByNom(@PathVariable String nom) {
        return service.getEntrepriseByNom(nom);
    }

    @GetMapping
    public List<Entreprise> getAllEntreprises() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Entreprise> searchEntreprises(@RequestParam(required = false) String searchTerm) {
        return service.searchEntreprises(searchTerm);
    }
}

