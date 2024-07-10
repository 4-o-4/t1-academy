package com.example.service;

import com.example.annotation.TrackAsyncTime;
import com.example.entity.Time;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;

public interface TimeService {
    List<Time> findAll();

    Optional<Time> findById(Long id);

    void deleteById(Long id);

    Time save(StopWatch stopWatch, TrackAsyncTime requestMethod);
}
