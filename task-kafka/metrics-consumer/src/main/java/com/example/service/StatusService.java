package com.example.service;

import com.example.dto.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<Status> findAll();

    Optional<Status> findById(Long id);

    void save(Status status);
}
