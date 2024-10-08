package com.example.controller;

import com.example.annotation.TrackAsyncTime;
import com.example.entity.RequestLog;
import com.example.service.RequestLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "TrackAsyncTime", description = "Система учета времени выполнения методов")
@RestController()
@RequestMapping("/api/v1/times")
public class RequestLogController {
    private final RequestLogService requestLogService;

    @Autowired
    public RequestLogController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }

    @Operation(tags = "TrackAsyncTime", summary = "Gets all trackTime")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the trackTime",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RequestLog.class))
            })
    })
    @GetMapping()
    @TrackAsyncTime
    public List<RequestLog> getAll() {
        return this.requestLogService.findAll();
    }

    @GetMapping("/{id}")
    @TrackAsyncTime
    public RequestLog get(@PathVariable Long id) {
        return this.requestLogService.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @TrackAsyncTime(RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.requestLogService.deleteById(id);
    }

    @PostMapping()
    @TrackAsyncTime(RequestMethod.POST)
    public long totalTrackTimeByMethod(@RequestBody String method) {
        Optional<RequestMethod> requestMethod = Optional.ofNullable(RequestMethod.resolve(method.toUpperCase()));
        return requestMethod.map(this.requestLogService::sumTrackTimeByRequestMethod).orElse(-1L);
    }
}
