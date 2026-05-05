package com.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.model.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {

}
