package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Encadrant_Entreprise;
import com.stage.repository.EncadrantEntrepriseRepository;

@Service
public class EncadrantEntrepriseService {

    @Autowired
    private EncadrantEntrepriseRepository encadrant_entreprise_repository;

    // Constructor injection for EncadrantEntrepriseRepository
    public EncadrantEntrepriseService(EncadrantEntrepriseRepository encadrant_entreprise_repository) {
        this.encadrant_entreprise_repository = encadrant_entreprise_repository;
    }

    // Add a new encadrant entreprise
    public Encadrant_Entreprise saveEncadrantEntreprise(Encadrant_Entreprise encadrant) {
        if (encadrant.getNom() == null || encadrant.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrant nom cannot be empty");
        }
        if (encadrant.getEmail() == null || encadrant.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrant email cannot be empty");
        }
        if (encadrant.getTelephone() == null || encadrant.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrant telephone cannot be empty");
        }

        if (encadrant_entreprise_repository.existsByEmail(encadrant.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (encadrant_entreprise_repository.existsByTelephone(encadrant.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return encadrant_entreprise_repository.save(encadrant);
    }

    // Delete an encadrant entreprise
    public void deleteEncadrantEntreprise(Long id) {
        if (!encadrant_entreprise_repository.existsById(id)) {
            throw new IllegalArgumentException("Encadrant not found with id: " + id);
        }
        encadrant_entreprise_repository.deleteById(id);
    }

    // Update an encadrant entreprise
    public Encadrant_Entreprise updateEncadrantEntreprise(Encadrant_Entreprise encadrant) {
        if (encadrant.getId_encadrant_entreprise() == null) {
            throw new IllegalArgumentException("Encadrant ID is required for update");
        }

        Encadrant_Entreprise existingEncadrant = encadrant_entreprise_repository
                .findById(encadrant.getId_encadrant_entreprise())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Encadrant not found with id: " + encadrant.getId_encadrant_entreprise()));

        if (encadrant.getNom() == null || encadrant.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrant nom cannot be empty");
        }
        if (encadrant.getEmail() == null || encadrant.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrant email cannot be empty");
        }
        if (encadrant.getTelephone() == null || encadrant.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Encadrant telephone cannot be empty");
        }

        // Check if new email already exists (and it's not the same encadrant)
        if (!existingEncadrant.getEmail().equals(encadrant.getEmail()) &&
                encadrant_entreprise_repository.existsByEmail(encadrant.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Check if new telephone already exists (and it's not the same encadrant)
        if (!existingEncadrant.getTelephone().equals(encadrant.getTelephone()) &&
                encadrant_entreprise_repository.existsByTelephone(encadrant.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return encadrant_entreprise_repository.save(encadrant);
    }

    // Get encadrant by ID
    public Encadrant_Entreprise getEncadrantById(Long id) {
        return encadrant_entreprise_repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encadrant not found with id: " + id));
    }

    // Get encadrant by nom
    public Encadrant_Entreprise getEncadrantByNom(String nom) {
        return encadrant_entreprise_repository.findByNom(nom)
                .orElseThrow(() -> new IllegalArgumentException("Encadrant not found with nom: " + nom));
    }

    // Search encadrants
    public List<Encadrant_Entreprise> searchEncadrants(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return encadrant_entreprise_repository.findAll();
        }
        return encadrant_entreprise_repository.searchEncadrants(searchTerm);
    }

    // Get all encadrants
    public List<Encadrant_Entreprise> getAll() {
        return encadrant_entreprise_repository.findAll();
    }
}
