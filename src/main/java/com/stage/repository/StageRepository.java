package com.stage.repository;

import java.util.Date;
import java.util.List;
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
}
