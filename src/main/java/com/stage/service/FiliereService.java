package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Filiere;
import com.stage.repository.FiliereRepository;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiere_repository;

    // Constructor
    public FiliereService(FiliereRepository filiere_repository) {
        this.filiere_repository = filiere_repository;
    }

    // ajout d'une filiere
    public Filiere saveFiliere(Filiere filiere) {
        if (filiere.getIntitule() == null || filiere.getIntitule().trim().isEmpty()) {
            throw new IllegalArgumentException("Filiere intitule cannot be empty");
        }
        if (filiere_repository.existsByIntitule(filiere.getIntitule())) {
            throw new IllegalArgumentException("Filiere with intitule '" + filiere.getIntitule() + "' already exists");
        }
        return filiere_repository.save(filiere);
    }

    // suppression d'une filiere
    public void deleteFiliere(Long id) {
        if (!filiere_repository.existsById(id)) {
            throw new IllegalArgumentException("Filiere not found with id: " + id);
        }
        filiere_repository.deleteById(id);
    }

    // modification d'une filiere
    public Filiere updateFiliere(Filiere filiere) {
        if (filiere.getId() == null) {
            throw new IllegalArgumentException("Filiere ID is required for update");
        }
        Filiere existingFiliere = filiere_repository.findById(filiere.getId())
                .orElseThrow(() -> new IllegalArgumentException("Filiere not found with id: " + filiere.getId()));

        if (filiere.getIntitule() == null || filiere.getIntitule().trim().isEmpty()) {
            throw new IllegalArgumentException("Filiere intitule cannot be empty");
        }

        // verfier si l'intitule est modifié et s'il existe déjà une filiere avec le
        // même intitule
        if (!existingFiliere.getIntitule().equals(filiere.getIntitule()) &&
                filiere_repository.existsByIntitule(filiere.getIntitule())) {
            throw new IllegalArgumentException("Filiere with intitule '" + filiere.getIntitule() + "' already exists");
        }

        return filiere_repository.save(filiere);
    }

    // affichage d'une filiere par id
    public Filiere getFiliereById(Long id) {
        return filiere_repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filiere not found with id: " + id));
    }

    // affichage d'une filiere par intitule
    public Filiere getFiliereByIntitule(String intitule) {
        return filiere_repository.findByIntitule(intitule)
                .orElseThrow(() -> new IllegalArgumentException("Filiere not found with intitule: " + intitule));
    }

    // affichage avec recherche
    public List<Filiere> searchFilieres(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return filiere_repository.findAll();
        }
        return filiere_repository.searchFilieres(searchTerm);
    }

    // affichage sans recherche
    public List<Filiere> getAll() {
        return filiere_repository.findAll();
    }
}
