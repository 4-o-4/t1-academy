package com.example.entity;

import com.example.annotation.TrackAsyncTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
@Entity
@Table(name = "times")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestMethod requestMethod;

    private String nameMethod;

    private long trackTime;

    public Time(TrackAsyncTime requestMethod, StopWatch stopWatch) {
        this(requestMethod.value(), stopWatch.lastTaskInfo().getTaskName(), stopWatch.lastTaskInfo().getTimeNanos());
    }

    public Time(RequestMethod requestMethod, String nameMethod, long trackTime) {
        this.requestMethod = requestMethod;
        this.nameMethod = nameMethod;
        this.trackTime = trackTime;
    }

    public Time() {
    }
}
