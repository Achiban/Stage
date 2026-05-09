package com.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Encadrement_Academique;
import com.stage.model.enums.Type_Encadrement_Academique;

public interface EncadrementAcademiqueRepository extends JpaRepository<Encadrement_Academique, Long> {

    // Find by nom
    Optional<Encadrement_Academique> findByNomEncadrantAcademique(String nomEncadrantAcademique);

    // Check if exists by nom
    boolean existsByNomEncadrantAcademique(String nomEncadrantAcademique);

    // Find by type
    List<Encadrement_Academique> findByTypeEncadrementAcademique(Type_Encadrement_Academique type);

    // Search encadrant by nom containing
    @Query("SELECT e FROM Encadrement_Academique e WHERE LOWER(e.nomEncadrantAcademique) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Encadrement_Academique> searchEncadrements(@Param("searchTerm") String searchTerm);
}
