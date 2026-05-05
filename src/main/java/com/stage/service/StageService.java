package com.stage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stage.model.Stage;
import com.stage.repository.StageRepository;

@Service
public class StageService {
    private final StageRepository repo;

    public StageService(StageRepository repo) {
        this.repo = repo;
    }

    public List<Stage> getAll() {
        return repo.findAll();
    }
}
