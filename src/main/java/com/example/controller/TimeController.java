package com.example.controller;

import com.example.annotation.TrackAsyncTime;
import com.example.entity.Time;
import com.example.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1/times")
public class TimeController {
    private final TimeService timeService;

    public TimeController(@Autowired TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping({"", "/"})
    @TrackAsyncTime
    public List<Time> getAll() {
        return this.timeService.findAll();
    }

    @GetMapping("/{id}")
    @TrackAsyncTime
    public Time get(@PathVariable Long id) {
        return this.timeService.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @TrackAsyncTime(RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.timeService.deleteById(id);
    }

    @PostMapping({"", "/"})
    @TrackAsyncTime(RequestMethod.POST)
    public long totalTrackTimeByMethod(@RequestBody String method) {
        Optional<RequestMethod> requestMethod = Optional.ofNullable(RequestMethod.resolve(method.toUpperCase()));
        return requestMethod.map(this.timeService::getTotalTrackTimeByMethod).orElse(-1L);
    }
}
