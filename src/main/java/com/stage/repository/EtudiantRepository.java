package com.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByCNE(String CNE);

    Boolean existsByTelephone(String telephone);

    @Query("SELECT c FROM Etudiant c WHERE  " +
            "(LOWER(c.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.CNE) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.telephone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Etudiant> searchEtudiants(@Param("searchTerm") String searchTerm);

}
