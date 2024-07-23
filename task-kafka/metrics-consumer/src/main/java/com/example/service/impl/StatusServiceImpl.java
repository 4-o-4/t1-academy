package com.example.service.impl;

import com.example.dto.Status;
import com.example.repositorie.StatusRepository;
import com.example.service.StatusService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> findAll() {
        return this.statusRepository.findAll();
    }

    @Override
    public Optional<Status> findById(Long id) {
        return this.statusRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Status status) {
        this.statusRepository.save(status);
    }
}
