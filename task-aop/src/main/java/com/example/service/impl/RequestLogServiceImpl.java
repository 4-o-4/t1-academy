package com.example.service.impl;

import com.example.annotation.TrackAsyncTime;
import com.example.entity.RequestLog;
import com.example.repositorie.RequestLogRepository;
import com.example.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Service
public class RequestLogServiceImpl implements RequestLogService {
    private final RequestLogRepository requestLogRepository;

    public RequestLogServiceImpl(@Autowired RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public List<RequestLog> findAll() {
        return this.requestLogRepository.findAll();
    }

    @Override
    public Optional<RequestLog> findById(Long id) {
        return this.requestLogRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.requestLogRepository.deleteById(id);
    }

    @Override
    public RequestLog save(StopWatch stopWatch, TrackAsyncTime requestMethod) {
        RequestLog entity = new RequestLog(requestMethod, stopWatch);
        return this.requestLogRepository.save(entity);
    }

    @Override
    public long sumTrackTimeByRequestMethod(RequestMethod method) {
        return this.requestLogRepository.sumTrackTimeByRequestMethod(method);
    }
}
