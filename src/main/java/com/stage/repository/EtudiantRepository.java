package com.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
