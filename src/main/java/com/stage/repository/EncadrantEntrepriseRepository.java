package com.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Encadrant_Entreprise;

public interface EncadrantEntrepriseRepository extends JpaRepository<Encadrant_Entreprise, Long> {

    // Find by nom
    Optional<Encadrant_Entreprise> findByNom(String nom);

    // Check if exists by nom
    boolean existsByNom(String nom);

    // Check if exists by email
    boolean existsByEmail(String email);

    // Check if exists by telephone
    boolean existsByTelephone(String telephone);

    // Search encadrant by nom containing
    @Query("SELECT e FROM Encadrant_Entreprise e WHERE LOWER(e.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(e.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Encadrant_Entreprise> searchEncadrants(@Param("searchTerm") String searchTerm);
}
