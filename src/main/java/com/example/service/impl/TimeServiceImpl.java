package com.example.service.impl;

import com.example.annotation.TrackAsyncTime;
import com.example.entity.Time;
import com.example.repositorie.TimeRepository;
import com.example.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;

@Service
public class TimeServiceImpl implements TimeService {
    private final TimeRepository timeRepository;

    public TimeServiceImpl(@Autowired TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Override
    public List<Time> findAll() {
        return this.timeRepository.findAll();
    }

    @Override
    public Optional<Time> findById(Long id) {
        return this.timeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.timeRepository.deleteById(id);
    }

    @Override
    public Time save(StopWatch stopWatch, TrackAsyncTime requestMethod) {
        Time entity = new Time(requestMethod, stopWatch);
        return this.timeRepository.save(entity);
    }
}
