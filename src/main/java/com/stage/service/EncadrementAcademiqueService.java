package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Encadrement_Academique;
import com.stage.model.enums.Type_Encadrement_Academique;
import com.stage.repository.EncadrementAcademiqueRepository;

@Service
public class EncadrementAcademiqueService {

    @Autowired
    private EncadrementAcademiqueRepository encadrement_academique_repository;

    // Constructor injection for EncadrementAcademiqueRepository
    public EncadrementAcademiqueService(EncadrementAcademiqueRepository encadrement_academique_repository) {
        this.encadrement_academique_repository = encadrement_academique_repository;
    }

    // Add a new encadrement academique
    public Encadrement_Academique saveEncadrementAcademique(Encadrement_Academique encadrement) {
        if (encadrement.getNomEncadrantAcademique() == null
                || encadrement.getNomEncadrantAcademique().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrement nom cannot be empty");
        }
        if (encadrement.getTypeEncadrementAcademique() == null) {
            throw new IllegalArgumentException("Encadrement type cannot be null");
        }

        if (encadrement_academique_repository.existsByNomEncadrantAcademique(encadrement.getNomEncadrantAcademique())) {
            throw new IllegalArgumentException(
                    "Encadrement with nom '" + encadrement.getNomEncadrantAcademique() + "' already exists");
        }

        return encadrement_academique_repository.save(encadrement);
    }

    // Delete an encadrement academique
    public void deleteEncadrementAcademique(Long id) {
        if (!encadrement_academique_repository.existsById(id)) {
            throw new IllegalArgumentException("Encadrement not found with id: " + id);
        }
        encadrement_academique_repository.deleteById(id);
    }

    // Update an encadrement academique
    public Encadrement_Academique updateEncadrementAcademique(Encadrement_Academique encadrement) {
        if (encadrement.getId_encadrement_academique() == null) {
            throw new IllegalArgumentException("Encadrement ID is required for update");
        }

        Encadrement_Academique existingEncadrement = encadrement_academique_repository
                .findById(encadrement.getId_encadrement_academique())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Encadrement not found with id: " + encadrement.getId_encadrement_academique()));

        if (encadrement.getNomEncadrantAcademique() == null
                || encadrement.getNomEncadrantAcademique().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrement nom cannot be empty");
        }
        if (encadrement.getTypeEncadrementAcademique() == null) {
            throw new IllegalArgumentException("Encadrement type cannot be null");
        }

        // Check if new nom already exists (and it's not the same encadrement)
        if (!existingEncadrement.getNomEncadrantAcademique().equals(encadrement.getNomEncadrantAcademique()) &&
                encadrement_academique_repository
                        .existsByNomEncadrantAcademique(encadrement.getNomEncadrantAcademique())) {
            throw new IllegalArgumentException(
                    "Encadrement with nom '" + encadrement.getNomEncadrantAcademique() + "' already exists");
        }

        return encadrement_academique_repository.save(encadrement);
    }

    // Get encadrement by ID
    public Encadrement_Academique getEncadrementById(Long id) {
        return encadrement_academique_repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encadrement not found with id: " + id));
    }

    // Get encadrement by nom
    public Encadrement_Academique getEncadrementByNom(String nom) {
        return encadrement_academique_repository.findByNomEncadrantAcademique(nom)
                .orElseThrow(() -> new IllegalArgumentException("Encadrement not found with nom: " + nom));
    }

    // Get encadrements by type
    public List<Encadrement_Academique> getEncadrementsByType(Type_Encadrement_Academique type) {
        return encadrement_academique_repository.findByTypeEncadrementAcademique(type);
    }

    // Search encadrements
    public List<Encadrement_Academique> searchEncadrements(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return encadrement_academique_repository.findAll();
        }
        return encadrement_academique_repository.searchEncadrements(searchTerm);
    }

    // Get all encadrements
    public List<Encadrement_Academique> getAll() {
        return encadrement_academique_repository.findAll();
    }
}
