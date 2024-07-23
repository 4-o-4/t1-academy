package com.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusTest {
    @Test
    void example1() {
        String status = "{\"status\":\"UP\"}";
        assertEquals(new Status("UP"), Status.parse(status));
    }

    @Test
    void example2() {
        String status = "{\"status\":\"ERROR\"}";
        assertEquals(new Status("ERROR"), Status.parse(status));
    }
}