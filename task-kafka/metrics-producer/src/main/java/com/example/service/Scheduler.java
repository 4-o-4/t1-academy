package com.example.service;

import com.example.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
public class Scheduler {
    private final Sender sender;

    @Autowired
    public Scheduler(Sender sender) {
        this.sender = sender;
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void schedulingTask() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:8080/actuator/health");
        URLConnection urlConnection = url.openConnection();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null)
                result.append(line);
            this.sender.send(1, result.toString());
        }
    }
}
