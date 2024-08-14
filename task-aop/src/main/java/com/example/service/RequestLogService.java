package com.example.service;

import com.example.annotation.TrackAsyncTime;
import com.example.entity.RequestLog;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public interface RequestLogService {
    List<RequestLog> findAll();

    Optional<RequestLog> findById(Long id);

    void deleteById(Long id);

    RequestLog save(StopWatch stopWatch, TrackAsyncTime requestMethod);

    long sumTrackTimeByRequestMethod(RequestMethod method);
}
