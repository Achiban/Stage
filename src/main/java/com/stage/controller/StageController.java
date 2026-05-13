package com.stage.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stage.model.Stage;
import com.stage.service.StageService;
import com.stage.util.PdfReportGenerator;

@RestController
@RequestMapping("/stages")
public class StageController {

    private final StageService service;

    public StageController(StageService service) {
        this.service = service;
    }

    @PostMapping
    public Stage createStage(@RequestBody Stage stage) {
        return service.saveStage(stage);
    }

    @PutMapping
    public Stage updateStage(@RequestBody Stage stage) {
        return service.updateStage(stage);
    }

    @DeleteMapping("/{id}")
    public void deleteStage(@PathVariable Long id) {
        service.deleteStage(id);
    }

    @GetMapping("/{id}")
    public Stage getStageById(@PathVariable Long id) {
        return service.getStageById(id);
    }

    @GetMapping("/sujet/{sujet}")
    public Stage getStageBySujet(@PathVariable String sujet) {
        return service.getStageBySujet(sujet);
    }

    @GetMapping
    public List<Stage> getAllStages() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Stage> searchStages(@RequestParam(required = false) String searchTerm) {
        return service.searchStages(searchTerm);
    }

    @GetMapping("/filter")
    public List<Stage> filterStages(@RequestParam(required = false) Long filiereId,
            @RequestParam(required = false) String anneeUniversitaire) {
        return service.filterStages(filiereId, anneeUniversitaire);
    }

    @GetMapping("/filiere/{filiereId}")
    public List<Stage> getStagesByFiliere(@PathVariable Long filiereId) {
        return service.getStagesByFiliere(filiereId);
    }

    @GetMapping("/annee/{anneeUniversitaire}")
    public List<Stage> getStagesByAnnee(@PathVariable String anneeUniversitaire) {
        return service.getStagesByAnneeUniversitaire(anneeUniversitaire);
    }

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        return service.getDashboard();
    }

    @GetMapping("/{id}/rapport")
    public ResponseEntity<byte[]> generateStageReport(@PathVariable Long id) {
        Stage stage = service.getStageById(id);
        byte[] pdf = PdfReportGenerator.generateStageReport(stage, new SimpleDateFormat("yyyy-MM-dd"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stage-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
