package com.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
