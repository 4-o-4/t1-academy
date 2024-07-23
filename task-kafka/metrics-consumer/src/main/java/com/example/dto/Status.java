package com.example.dto;

import lombok.Data;

@Data
public class Status {
    private String status;

    public Status(String status) {
        this.status = status;
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
