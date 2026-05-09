package com.stage.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stage.model.Stage;
import com.stage.repository.StageRepository;

@Service
public class StageService {
    private final StageRepository repo;

    public StageService(StageRepository repo) {
        this.repo = repo;
    }

    // Add a new stage
    public Stage saveStage(Stage stage) {
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

        if (repo.existsBySujet(stage.getSujet())) {
            throw new IllegalArgumentException("Stage with sujet '" + stage.getSujet() + "' already exists");
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

        // Check if new sujet already exists (and it's not the same stage)
        if (!existingStage.getSujet().equals(stage.getSujet()) && repo.existsBySujet(stage.getSujet())) {
            throw new IllegalArgumentException("Stage with sujet '" + stage.getSujet() + "' already exists");
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

    // Get all stages
    public List<Stage> getAll() {
        return repo.findAll();
    }
}
