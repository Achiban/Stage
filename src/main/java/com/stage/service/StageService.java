package com.stage.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stage.model.Stage;
import com.stage.repository.EncadrantEntrepriseRepository;
import com.stage.repository.EncadrementAcademiqueRepository;
import com.stage.repository.EntrepriseRepository;
import com.stage.repository.EtudiantRepository;
import com.stage.repository.StageRepository;

@Service
public class StageService {
    private final StageRepository repo;
    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EncadrementAcademiqueRepository encadrementAcademiqueRepository;
    private final EncadrantEntrepriseRepository encadrantEntrepriseRepository;

    public StageService(
            StageRepository repo,
            EtudiantRepository etudiantRepository,
            EntrepriseRepository entrepriseRepository,
            EncadrementAcademiqueRepository encadrementAcademiqueRepository,
            EncadrantEntrepriseRepository encadrantEntrepriseRepository) {
        this.repo = repo;
        this.etudiantRepository = etudiantRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.encadrementAcademiqueRepository = encadrementAcademiqueRepository;
        this.encadrantEntrepriseRepository = encadrantEntrepriseRepository;
    }

    // Add a new stage
    public Stage saveStage(Stage stage) {
        validateStage(stage);
        attachRelations(stage);

        if (repo.existsBySujet(stage.getSujet())) {
            throw new IllegalArgumentException("Stage with sujet '" + stage.getSujet() + "' already exists");
        }
        if (repo.existsByEtudiantId(stage.getEtudiant().getId_etudiant())) {
            throw new IllegalArgumentException("Cet etudiant a deja un stage affecte");
        }

        return repo.save(stage);
    }

    // Delete a stage
    public void deleteStage(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Stage not found with id: " + id);
        }
        repo.deleteById(id);
    }

    // Update a stage
    public Stage updateStage(Stage stage) {
        if (stage.getId_Stage() == null) {
            throw new IllegalArgumentException("Stage ID is required for update");
        }

        Stage existingStage = repo.findById(stage.getId_Stage())
                .orElseThrow(() -> new IllegalArgumentException("Stage not found with id: " + stage.getId_Stage()));

        validateStage(stage);
        attachRelations(stage);

        // Check if new sujet already exists (and it's not the same stage)
        if (!existingStage.getSujet().equals(stage.getSujet()) && repo.existsBySujet(stage.getSujet())) {
            throw new IllegalArgumentException("Stage with sujet '" + stage.getSujet() + "' already exists");
        }
        if (repo.existsByEtudiantIdAndIdNot(stage.getEtudiant().getId_etudiant(), stage.getId_Stage())) {
            throw new IllegalArgumentException("Cet etudiant a deja un autre stage affecte");
        }

        return repo.save(stage);
    }

    // Get stage by ID
    public Stage getStageById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stage not found with id: " + id));
    }

    // Get stage by sujet
    public Stage getStageBySujet(String sujet) {
        return repo.findBySujet(sujet)
                .orElseThrow(() -> new IllegalArgumentException("Stage not found with sujet: " + sujet));
    }

    // Get stages by date range
    public List<Stage> getStagesByDateRange(Date startDate, Date endDate) {
        return repo.findStagesByDateRange(startDate, endDate);
    }

    // Get stages starting after a date
    public List<Stage> getStagesStartingAfter(Date date) {
        return repo.findStagesStartingAfter(date);
    }

    // Get stages ending before a date
    public List<Stage> getStagesEndingBefore(Date date) {
        return repo.findStagesEndingBefore(date);
    }

    // Search stages
    public List<Stage> searchStages(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return repo.findAll();
        }
        return repo.searchStages(searchTerm);
    }

    public List<Stage> getStagesByFiliere(Long filiereId) {
        return repo.findByFiliereId(filiereId);
    }

    public List<Stage> getStagesByAnneeUniversitaire(String anneeUniversitaire) {
        return repo.findByAnneeUniversitaire(anneeUniversitaire);
    }

    public List<Stage> filterStages(Long filiereId, String anneeUniversitaire) {
        return repo.filterStages(filiereId, anneeUniversitaire);
    }

    public Map<String, Object> getDashboard() {
        Map<String, Object> dashboard = new LinkedHashMap<>();
        List<Stage> stages = repo.findAll();

        long stagesAvecEncadrementComplet = stages.stream()
                .filter(stage -> stage.getEncadrementAcademique() != null
                        && stage.getEncadrantEntreprise() != null
                        && stage.getEntreprise() != null
                        && stage.getEtudiant() != null)
                .count();

        dashboard.put("totalStages", stages.size());
        dashboard.put("totalStagesAffectes", stages.stream().filter(stage -> stage.getEtudiant() != null).count());
        dashboard.put("totalStagesAvecEncadrementComplet", stagesAvecEncadrementComplet);
        dashboard.put("stagesParAnnee", repo.countStagesByAnnee());
        return dashboard;
    }

    // Get all stages
    public List<Stage> getAll() {
        return repo.findAll();
    }

    private void attachRelations(Stage stage) {
        stage.setEtudiant(
                etudiantRepository.findById(stage.getEtudiant().getId_etudiant())
                        .orElseThrow(() -> new IllegalArgumentException("Etudiant not found with id: "
                                + stage.getEtudiant().getId_etudiant())));
        stage.setEntreprise(
                entrepriseRepository.findById(stage.getEntreprise().getId_entreprise())
                        .orElseThrow(() -> new IllegalArgumentException("Entreprise not found with id: "
                                + stage.getEntreprise().getId_entreprise())));
        stage.setEncadrementAcademique(
                encadrementAcademiqueRepository
                        .findById(stage.getEncadrementAcademique().getId_encadrement_academique())
                        .orElseThrow(() -> new IllegalArgumentException("Encadrement not found with id: "
                                + stage.getEncadrementAcademique().getId_encadrement_academique())));
        stage.setEncadrantEntreprise(
                encadrantEntrepriseRepository.findById(stage.getEncadrantEntreprise().getId_encadrant_entreprise())
                        .orElseThrow(() -> new IllegalArgumentException("Encadrant not found with id: "
                                + stage.getEncadrantEntreprise().getId_encadrant_entreprise())));
    }

    private void validateStage(Stage stage) {
        if (stage.getSujet() == null || stage.getSujet().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage sujet cannot be empty");
        }
        if (stage.getDate_debut() == null) {
            throw new IllegalArgumentException("Stage date_debut cannot be null");
        }
        if (stage.getDate_fin() == null) {
            throw new IllegalArgumentException("Stage date_fin cannot be null");
        }
        if (stage.getDate_debut().after(stage.getDate_fin())) {
            throw new IllegalArgumentException("Date debut cannot be after date fin");
        }
        if (stage.getDescription() == null || stage.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage description cannot be empty");
        }
        if (stage.getObjectifs() == null || stage.getObjectifs().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage objectifs cannot be empty");
        }
        if (stage.getSolution() == null || stage.getSolution().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage solution cannot be empty");
        }
        if (stage.getDemarche() == null || stage.getDemarche().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage demarche cannot be empty");
        }
        if (stage.getOutils() == null || stage.getOutils().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage outils cannot be empty");
        }
        if (stage.getEnvironnement() == null || stage.getEnvironnement().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage environnement cannot be empty");
        }
        if (stage.getAnneeUniversitaire() == null || stage.getAnneeUniversitaire().trim().isEmpty()) {
            throw new IllegalArgumentException("L'annee universitaire est obligatoire");
        }
        if (stage.getEtudiant() == null || stage.getEtudiant().getId_etudiant() == null) {
            throw new IllegalArgumentException("L'etudiant affecte est obligatoire");
        }
        if (stage.getEntreprise() == null || stage.getEntreprise().getId_entreprise() == null) {
            throw new IllegalArgumentException("L'entreprise d'accueil est obligatoire");
        }
        if (stage.getEncadrementAcademique() == null
                || stage.getEncadrementAcademique().getId_encadrement_academique() == null) {
            throw new IllegalArgumentException("L'encadrement academique est obligatoire");
        }
        if (stage.getEncadrantEntreprise() == null
                || stage.getEncadrantEntreprise().getId_encadrant_entreprise() == null) {
            throw new IllegalArgumentException("L'encadrant professionnel est obligatoire");
        }
    }
}
