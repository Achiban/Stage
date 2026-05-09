package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Entreprise;
import com.stage.repository.EntrepriseRepository;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entreprise_repository;

    // Constructor injection for EntrepriseRepository
    public EntrepriseService(EntrepriseRepository entreprise_repository) {
        this.entreprise_repository = entreprise_repository;
    }

    // Add a new entreprise
    public Entreprise saveEntreprise(Entreprise entreprise) {
        if (entreprise.getNom() == null || entreprise.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Entreprise nom cannot be empty");
        }
        if (entreprise.getEmail() == null || entreprise.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Entreprise email cannot be empty");
        }
        if (entreprise.getTelephone() == null || entreprise.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Entreprise telephone cannot be empty");
        }

        if (entreprise_repository.existsByNom(entreprise.getNom())) {
            throw new IllegalArgumentException("Entreprise with nom '" + entreprise.getNom() + "' already exists");
        }
        if (entreprise_repository.existsByEmail(entreprise.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (entreprise_repository.existsByTelephone(entreprise.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return entreprise_repository.save(entreprise);
    }

    // Delete an entreprise
    public void deleteEntreprise(Long id) {
        if (!entreprise_repository.existsById(id)) {
            throw new IllegalArgumentException("Entreprise not found with id: " + id);
        }
        entreprise_repository.deleteById(id);
    }

    // Update an entreprise
    public Entreprise updateEntreprise(Entreprise entreprise) {
        if (entreprise.getId_entreprise() == null) {
            throw new IllegalArgumentException("Entreprise ID is required for update");
        }

        Entreprise existingEntreprise = entreprise_repository.findById(entreprise.getId_entreprise())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Entreprise not found with id: " + entreprise.getId_entreprise()));

        if (entreprise.getNom() == null || entreprise.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Entreprise nom cannot be empty");
        }
        if (entreprise.getEmail() == null || entreprise.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Entreprise email cannot be empty");
        }
        if (entreprise.getTelephone() == null || entreprise.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Entreprise telephone cannot be empty");
        }

        // Check if new nom already exists (and it's not the same entreprise)
        if (!existingEntreprise.getNom().equals(entreprise.getNom()) &&
                entreprise_repository.existsByNom(entreprise.getNom())) {
            throw new IllegalArgumentException("Entreprise with nom '" + entreprise.getNom() + "' already exists");
        }

        // Check if new email already exists (and it's not the same entreprise)
        if (!existingEntreprise.getEmail().equals(entreprise.getEmail()) &&
                entreprise_repository.existsByEmail(entreprise.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Check if new telephone already exists (and it's not the same entreprise)
        if (!existingEntreprise.getTelephone().equals(entreprise.getTelephone()) &&
                entreprise_repository.existsByTelephone(entreprise.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return entreprise_repository.save(entreprise);
    }

    // Get entreprise by ID
    public Entreprise getEntrepriseById(Long id) {
        return entreprise_repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entreprise not found with id: " + id));
    }

    // Get entreprise by nom
    public Entreprise getEntrepriseByNom(String nom) {
        return entreprise_repository.findByNom(nom)
                .orElseThrow(() -> new IllegalArgumentException("Entreprise not found with nom: " + nom));
    }

    // Search entreprises
    public List<Entreprise> searchEntreprises(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return entreprise_repository.findAll();
        }
        return entreprise_repository.searchEntreprises(searchTerm);
    }

    // Get all entreprises
    public List<Entreprise> getAll() {
        return entreprise_repository.findAll();
    }
}
