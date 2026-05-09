package com.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Responsable_Entreprise;

public interface ResponsableEntrepriseRepository extends JpaRepository<Responsable_Entreprise, Long> {

    // Find by nom
    Optional<Responsable_Entreprise> findByNom(String nom);

    // Find by email
    Optional<Responsable_Entreprise> findByEmail(String email);

    // Check if exists by nom
    boolean existsByNom(String nom);

    // Check if exists by email
    boolean existsByEmail(String email);

    // Search responsable by nom or prenom containing
    @Query("SELECT r FROM Responsable_Entreprise r WHERE LOWER(r.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(r.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(r.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Responsable_Entreprise> searchResponsables(@Param("searchTerm") String searchTerm);
}
