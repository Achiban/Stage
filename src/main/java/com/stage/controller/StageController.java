package com.stage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.model.Stage;
import com.stage.service.StageService;

@RestController
@RequestMapping("/stages")
public class StageController {

    private final StageService service;

    public StageController(StageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Stage> getAllStages() {
        return service.getAll();
    }
}
