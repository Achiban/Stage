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

import com.stage.model.Responsable_Filiere;
import com.stage.service.ResponsableFiliereService;

@RestController
@RequestMapping("/responsables-filieres")
public class ResponsableFiliereController {

    private final ResponsableFiliereService service;

    public ResponsableFiliereController(ResponsableFiliereService service) {
        this.service = service;
    }

    @PostMapping
    public Responsable_Filiere createResponsableFiliere(@RequestBody Responsable_Filiere responsable) {
        return service.saveResponsableFiliere(responsable);
    }

    @PutMapping
    public Responsable_Filiere updateResponsableFiliere(@RequestBody Responsable_Filiere responsable) {
        return service.updateResponsableFiliere(responsable);
    }

    @DeleteMapping("/{id}")
    public void deleteResponsableFiliere(@PathVariable Long id) {
        service.deleteResponsableFiliere(id);
    }

    @GetMapping("/{id}")
    public Responsable_Filiere getResponsableFiliereById(@PathVariable Long id) {
        return service.getResponsableById(id);
    }

    @GetMapping("/nom/{nom}")
    public Responsable_Filiere getResponsableFiliereByNom(@PathVariable String nom) {
        return service.getResponsableByNom(nom);
    }

    @GetMapping("/email/{email}")
    public Responsable_Filiere getResponsableFiliereByEmail(@PathVariable String email) {
        return service.getResponsableByEmail(email);
    }

    @GetMapping
    public List<Responsable_Filiere> getAllResponsablesFilieres() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Responsable_Filiere> searchResponsablesFilieres(@RequestParam(required = false) String searchTerm) {
        return service.searchResponsables(searchTerm);
    }
}
