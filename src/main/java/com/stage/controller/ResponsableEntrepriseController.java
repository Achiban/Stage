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

import com.stage.model.Responsable_Entreprise;
import com.stage.service.ResponsableEntrepriseService;

@RestController
@RequestMapping("/responsables-entreprises")
public class ResponsableEntrepriseController {

    private final ResponsableEntrepriseService service;

    public ResponsableEntrepriseController(ResponsableEntrepriseService service) {
        this.service = service;
    }

    @PostMapping
    public Responsable_Entreprise createResponsableEntreprise(@RequestBody Responsable_Entreprise responsable) {
        return service.saveResponsableEntreprise(responsable);
    }

    @PutMapping
    public Responsable_Entreprise updateResponsableEntreprise(@RequestBody Responsable_Entreprise responsable) {
        return service.updateResponsableEntreprise(responsable);
    }

    @DeleteMapping("/{id}")
    public void deleteResponsableEntreprise(@PathVariable Long id) {
        service.deleteResponsableEntreprise(id);
    }

    @GetMapping("/{id}")
    public Responsable_Entreprise getResponsableEntrepriseById(@PathVariable Long id) {
        return service.getResponsableById(id);
    }

    @GetMapping("/nom/{nom}")
    public Responsable_Entreprise getResponsableEntrepriseByNom(@PathVariable String nom) {
        return service.getResponsableByNom(nom);
    }

    @GetMapping("/email/{email}")
    public Responsable_Entreprise getResponsableEntrepriseByEmail(@PathVariable String email) {
        return service.getResponsableByEmail(email);
    }

    @GetMapping
    public List<Responsable_Entreprise> getAllResponsablesEntreprises() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Responsable_Entreprise> searchResponsablesEntreprises(
            @RequestParam(required = false) String searchTerm) {
        return service.searchResponsables(searchTerm);
    }
}
