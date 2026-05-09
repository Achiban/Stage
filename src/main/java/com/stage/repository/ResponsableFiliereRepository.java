package com.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Responsable_Filiere;

public interface ResponsableFiliereRepository extends JpaRepository<Responsable_Filiere, Long> {

    // Find by nom
    Optional<Responsable_Filiere> findByNom(String nom);

    // Find by email
    Optional<Responsable_Filiere> findByEmail(String email);

    // Find by grade
    List<Responsable_Filiere> findByGrade(String grade);

    // Check if exists by nom
    boolean existsByNom(String nom);

    // Check if exists by email
    boolean existsByEmail(String email);

    // Check if exists by telephone
    boolean existsByTelephone(String telephone);

    // Search responsable by nom, prenom, or email containing
    @Query("SELECT r FROM Responsable_Filiere r WHERE LOWER(r.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(r.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(r.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Responsable_Filiere> searchResponsables(@Param("searchTerm") String searchTerm);
}
