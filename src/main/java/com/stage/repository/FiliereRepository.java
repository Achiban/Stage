package com.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.model.Filiere;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {

    // Find by intitule
    Optional<Filiere> findByIntitule(String intitule);

    // Check if exists by intitule
    boolean existsByIntitule(String intitule);

    // Search filiere by intitule containing
    @Query("SELECT f FROM Filiere f WHERE LOWER(f.intitule) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Filiere> searchFilieres(@Param("searchTerm") String searchTerm);
}
