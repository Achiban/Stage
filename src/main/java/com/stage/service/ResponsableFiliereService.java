package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Responsable_Filiere;
import com.stage.repository.ResponsableFiliereRepository;

@Service
public class ResponsableFiliereService {

    @Autowired
    private ResponsableFiliereRepository responsable_filiere_repository;

    // Constructor injection for ResponsableFiliereRepository
    public ResponsableFiliereService(ResponsableFiliereRepository responsable_filiere_repository) {
        this.responsable_filiere_repository = responsable_filiere_repository;
    }

    // Add a new responsable filiere
    public Responsable_Filiere saveResponsableFiliere(Responsable_Filiere responsable) {
        if (responsable.getNom() == null || responsable.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable nom cannot be empty");
        }
        if (responsable.getPrenom() == null || responsable.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable prenom cannot be empty");
        }
        if (responsable.getGrade() == null || responsable.getGrade().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable grade cannot be empty");
        }
        if (responsable.getEmail() == null || responsable.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable email cannot be empty");
        }
        if (responsable.getTelephone() == null || responsable.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable telephone cannot be empty");
        }

        if (responsable_filiere_repository.existsByEmail(responsable.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (responsable_filiere_repository.existsByTelephone(responsable.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return responsable_filiere_repository.save(responsable);
    }

    // Delete a responsable filiere
    public void deleteResponsableFiliere(Long id) {
        if (!responsable_filiere_repository.existsById(id)) {
            throw new IllegalArgumentException("Responsable not found with id: " + id);
        }
        responsable_filiere_repository.deleteById(id);
    }

    // Update a responsable filiere
    public Responsable_Filiere updateResponsableFiliere(Responsable_Filiere responsable) {
        if (responsable.getId_responsable() == null) {
            throw new IllegalArgumentException("Responsable ID is required for update");
        }

        Responsable_Filiere existingResponsable = responsable_filiere_repository
                .findById(responsable.getId_responsable())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Responsable not found with id: " + responsable.getId_responsable()));

        if (responsable.getNom() == null || responsable.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable nom cannot be empty");
        }
        if (responsable.getPrenom() == null || responsable.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable prenom cannot be empty");
        }
        if (responsable.getGrade() == null || responsable.getGrade().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable grade cannot be empty");
        }
        if (responsable.getEmail() == null || responsable.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable email cannot be empty");
        }
        if (responsable.getTelephone() == null || responsable.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Responsable telephone cannot be empty");
        }

        // Check if new email already exists (and it's not the same responsable)
        if (!existingResponsable.getEmail().equals(responsable.getEmail()) &&
                responsable_filiere_repository.existsByEmail(responsable.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Check if new telephone already exists (and it's not the same responsable)
        if (!existingResponsable.getTelephone().equals(responsable.getTelephone()) &&
                responsable_filiere_repository.existsByTelephone(responsable.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return responsable_filiere_repository.save(responsable);
    }

    // Get responsable by ID
    public Responsable_Filiere getResponsableById(Long id) {
        return responsable_filiere_repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Responsable not found with id: " + id));
    }

    // Get responsable by nom
    public Responsable_Filiere getResponsableByNom(String nom) {
        return responsable_filiere_repository.findByNom(nom)
                .orElseThrow(() -> new IllegalArgumentException("Responsable not found with nom: " + nom));
    }

    // Get responsable by email
    public Responsable_Filiere getResponsableByEmail(String email) {
        return responsable_filiere_repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Responsable not found with email: " + email));
    }

    // Get responsables by grade
    public List<Responsable_Filiere> getResponsablesByGrade(String grade) {
        return responsable_filiere_repository.findByGrade(grade);
    }

    // Search responsables
    public List<Responsable_Filiere> searchResponsables(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return responsable_filiere_repository.findAll();
        }
        return responsable_filiere_repository.searchResponsables(searchTerm);
    }

    // Get all responsables
    public List<Responsable_Filiere> getAll() {
        return responsable_filiere_repository.findAll();
    }
}
