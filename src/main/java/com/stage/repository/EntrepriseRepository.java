package com.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    // Find by nom
    Optional<Entreprise> findByNom(String nom);

    // Check if exists by nom
    boolean existsByNom(String nom);

    // Check if exists by email
    boolean existsByEmail(String email);

    // Check if exists by telephone
    boolean existsByTelephone(String telephone);

    // Search entreprise by nom containing
    @Query("SELECT e FROM Entreprise e WHERE LOWER(e.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(e.ville) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Entreprise> searchEntreprises(@Param("searchTerm") String searchTerm);
}
