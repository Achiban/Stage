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

import com.stage.model.Filiere;
import com.stage.service.FiliereService;

@RestController
@RequestMapping("/filieres")
public class FiliereController {

    private final FiliereService service;

    public FiliereController(FiliereService service) {
        this.service = service;
    }

    @PostMapping
    public Filiere createFiliere(@RequestBody Filiere filiere) {
        return service.saveFiliere(filiere);
    }

    @PutMapping
    public Filiere updateFiliere(@RequestBody Filiere filiere) {
        return service.updateFiliere(filiere);
    }

    @DeleteMapping("/{id}")
    public void deleteFiliere(@PathVariable Long id) {
        service.deleteFiliere(id);
    }

    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable Long id) {
        return service.getFiliereById(id);
    }

    @GetMapping("/intitule/{intitule}")
    public Filiere getFiliereByIntitule(@PathVariable String intitule) {
        return service.getFiliereByIntitule(intitule);
    }

    @GetMapping
    public List<Filiere> getAllFilieres() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Filiere> searchFilieres(@RequestParam(required = false) String searchTerm) {
        return service.searchFilieres(searchTerm);
    }
}

