package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Responsable_Entreprise;
import com.stage.repository.ResponsableEntrepriseRepository;

@Service
public class ResponsableEntrepriseService {

    @Autowired
    private ResponsableEntrepriseRepository responsable_entreprise_repository;

    // Constructor injection for ResponsableEntrepriseRepository
    public ResponsableEntrepriseService(ResponsableEntrepriseRepository responsable_entreprise_repository) {
        this.responsable_entreprise_repository = responsable_entreprise_repository;
    }

    // Add a new responsable entreprise
    public Responsable_Entreprise saveResponsableEntreprise(Responsable_Entreprise responsable) {
        if (responsable.getNom() == null || responsable.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable nom cannot be empty");
        }
        if (responsable.getPrenom() == null || responsable.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable prenom cannot be empty");
        }
        if (responsable.getEmail() == null || responsable.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable email cannot be empty");
        }

        if (responsable_entreprise_repository.existsByEmail(responsable.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return responsable_entreprise_repository.save(responsable);
    }

    // Delete a responsable entreprise
    public void deleteResponsableEntreprise(Long id) {
        if (!responsable_entreprise_repository.existsById(id)) {
            throw new IllegalArgumentException("Responsable not found with id: " + id);
        }
        responsable_entreprise_repository.deleteById(id);
    }

    // Update a responsable entreprise
    public Responsable_Entreprise updateResponsableEntreprise(Responsable_Entreprise responsable) {
        if (responsable.getId_responsable_entreprise() == null) {
            throw new IllegalArgumentException("Responsable ID is required for update");
        }

        Responsable_Entreprise existingResponsable = responsable_entreprise_repository
                .findById(responsable.getId_responsable_entreprise())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Responsable not found with id: " + responsable.getId_responsable_entreprise()));

        if (responsable.getNom() == null || responsable.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable nom cannot be empty");
        }
        if (responsable.getPrenom() == null || responsable.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable prenom cannot be empty");
        }
        if (responsable.getEmail() == null || responsable.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable email cannot be empty");
        }

        // Check if new email already exists (and it's not the same responsable)
        if (!existingResponsable.getEmail().equals(responsable.getEmail()) &&
                responsable_entreprise_repository.existsByEmail(responsable.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return responsable_entreprise_repository.save(responsable);
    }

    // Get responsable by ID
    public Responsable_Entreprise getResponsableById(Long id) {
        return responsable_entreprise_repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Responsable not found with id: " + id));
    }

    // Get responsable by nom
    public Responsable_Entreprise getResponsableByNom(String nom) {
        return responsable_entreprise_repository.findByNom(nom)
                .orElseThrow(() -> new IllegalArgumentException("Responsable not found with nom: " + nom));
    }

    // Get responsable by email
    public Responsable_Entreprise getResponsableByEmail(String email) {
        return responsable_entreprise_repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Responsable not found with email: " + email));
    }

    // Search responsables
    public List<Responsable_Entreprise> searchResponsables(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return responsable_entreprise_repository.findAll();
        }
        return responsable_entreprise_repository.searchResponsables(searchTerm);
    }

    // Get all responsables
    public List<Responsable_Entreprise> getAll() {
        return responsable_entreprise_repository.findAll();
    }
}
