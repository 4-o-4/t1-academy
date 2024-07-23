package com.example.controller;

import com.example.dto.Status;
import com.example.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/metrics")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping()
    public List<Status> getAll() {
        return this.statusService.findAll();
    }

    @GetMapping("/{id}")
    public Status get(@PathVariable Long id) {
        return this.statusService.findById(id).orElseThrow();
    }
}
