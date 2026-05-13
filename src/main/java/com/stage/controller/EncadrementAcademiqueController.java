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

import com.stage.model.Encadrement_Academique;
import com.stage.service.EncadrementAcademiqueService;

@RestController
@RequestMapping("/encadrements-academiques")
public class EncadrementAcademiqueController {

    private final EncadrementAcademiqueService service;

    public EncadrementAcademiqueController(EncadrementAcademiqueService service) {
        this.service = service;
    }

    @PostMapping
    public Encadrement_Academique createEncadrementAcademique(@RequestBody Encadrement_Academique encadrement) {
        return service.saveEncadrementAcademique(encadrement);
    }

    @PutMapping
    public Encadrement_Academique updateEncadrementAcademique(@RequestBody Encadrement_Academique encadrement) {
        return service.updateEncadrementAcademique(encadrement);
    }

    @DeleteMapping("/{id}")
    public void deleteEncadrementAcademique(@PathVariable Long id) {
        service.deleteEncadrementAcademique(id);
    }

    @GetMapping("/{id}")
    public Encadrement_Academique getEncadrementAcademiqueById(@PathVariable Long id) {
        return service.getEncadrementById(id);
    }

    @GetMapping("/nom/{nom}")
    public Encadrement_Academique getEncadrementAcademiqueByNom(@PathVariable String nom) {
        return service.getEncadrementByNom(nom);
    }

    @GetMapping
    public List<Encadrement_Academique> getAllEncadrementsAcademiques() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Encadrement_Academique> searchEncadrementsAcademiques(
            @RequestParam(required = false) String searchTerm) {
        return service.searchEncadrements(searchTerm);
    }
}
