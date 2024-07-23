package com.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private Date created = new Date();

    public Status(String status) {
        this.status = status;
    }

    public Status() {
    }

    public static Status parse(String str) {
        int beginIndex = 0;
        int endIndex = 0;

        for (int i = str.length() - 1; i != 0; i--) {
            if (str.charAt(i) == '\"') {
                if (endIndex == 0)
                    endIndex = i;
                else {
                    beginIndex = i + 1;
                    break;
                }
            }
        }
        return new Status(str.substring(beginIndex, endIndex));
    }
}
