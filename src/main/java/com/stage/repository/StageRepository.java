package com.stage.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {

    // Find by sujet
    @Query("SELECT s FROM Stage s WHERE s.sujet = :sujet")
    Optional<Stage> findBySujet(@Param("sujet") String sujet);

    // Check if exists by sujet
    @Query("SELECT COUNT(s) > 0 FROM Stage s WHERE s.sujet = :sujet")
    boolean existsBySujet(@Param("sujet") String sujet);

    // Find stages by date range
    @Query("SELECT s FROM Stage s WHERE s.date_debut >= :startDate AND s.date_fin <= :endDate")
    List<Stage> findStagesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Find stages starting after a date
    @Query("SELECT s FROM Stage s WHERE s.date_debut > :date")
    List<Stage> findStagesStartingAfter(@Param("date") Date date);

    // Find stages ending before a date
    @Query("SELECT s FROM Stage s WHERE s.date_fin < :date")
    List<Stage> findStagesEndingBefore(@Param("date") Date date);

    // Search stages by sujet, description, or objectifs containing
    @Query("SELECT s FROM Stage s WHERE LOWER(s.sujet) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.objectifs) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Stage> searchStages(@Param("searchTerm") String searchTerm);

    @Query("SELECT s FROM Stage s WHERE s.etudiant.filiere.id = :filiereId")
    List<Stage> findByFiliereId(@Param("filiereId") Long filiereId);

    List<Stage> findByAnneeUniversitaire(String anneeUniversitaire);

    @Query("""
            SELECT s FROM Stage s
            WHERE (:filiereId IS NULL OR s.etudiant.filiere.id = :filiereId)
              AND (:anneeUniversitaire IS NULL OR s.anneeUniversitaire = :anneeUniversitaire)
            """)
    List<Stage> filterStages(@Param("filiereId") Long filiereId,
            @Param("anneeUniversitaire") String anneeUniversitaire);

    @Query("SELECT COUNT(s) > 0 FROM Stage s WHERE s.etudiant.id = :etudiantId")
    boolean existsByEtudiantId(@Param("etudiantId") Long etudiantId);

    @Query("""
            SELECT COUNT(s) > 0 FROM Stage s
            WHERE s.etudiant.id = :etudiantId
              AND s.id <> :stageId
            """)
    boolean existsByEtudiantIdAndIdNot(@Param("etudiantId") Long etudiantId, @Param("stageId") Long stageId);

    @Query("SELECT s.anneeUniversitaire as annee, COUNT(s) as total FROM Stage s GROUP BY s.anneeUniversitaire")
    List<Map<String, Object>> countStagesByAnnee();
}
