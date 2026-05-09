package com.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.model.Etudiant;
import com.stage.repository.EtudiantRepository;




@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiant_repository;

    // Constructor injection for EtudiantRepository
    public EtudiantService(EtudiantRepository etudiant_repository) {
        this.etudiant_repository = etudiant_repository;
    }

    //ajout d'un etudiant
    public Etudiant saveEtudiant(Etudiant etudiant) {
        if (etudiant_repository.existsByEmail(etudiant.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (etudiant_repository.existsByCNE(etudiant.getCNE())) {
            throw new IllegalArgumentException("CNE already exists");
        }
        if (etudiant_repository.existsByTelephone(etudiant.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }
        return etudiant_repository.save(etudiant);
    }

    //suppression d'un etudiant
    public void deleteEtudiant(Long id) {
        etudiant_repository.deleteById(id);
    }

    //modification d'un etudiant
    public Etudiant updateEtudiant(Etudiant etudiant) {
        if (etudiant.getId_etudiant() == null) {
            throw new IllegalArgumentException("Etudiant ID is required for update");
        }
        Etudiant existingEtudiant = etudiant_repository.findById(etudiant.getId_etudiant())
                .orElseThrow(() -> new IllegalArgumentException("Etudiant not found"));

        if (!existingEtudiant.getEmail().equals(etudiant.getEmail()) && etudiant_repository.existsByEmail(etudiant.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (!existingEtudiant.getCNE().equals(etudiant.getCNE()) && etudiant_repository.existsByCNE(etudiant.getCNE())) {
            throw new IllegalArgumentException("CNE already exists");
        }
        if (!existingEtudiant.getTelephone().equals(etudiant.getTelephone()) && etudiant_repository.existsByTelephone(etudiant.getTelephone())) {
            throw new IllegalArgumentException("Telephone already exists");
        }

        return etudiant_repository.save(etudiant);
    }


    //affichage avec recherche
    public List<Etudiant> searchEtudiants(String searchTerm) {
        return etudiant_repository.searchEtudiants(searchTerm);
    }


    //affichage sans recherche
    public List<Etudiant> getAll() {
        return etudiant_repository.findAll();
    }
}
