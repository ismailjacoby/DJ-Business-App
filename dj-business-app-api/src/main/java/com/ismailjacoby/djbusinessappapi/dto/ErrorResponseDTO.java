package com.ismailjacoby.djbusinessappapi.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponseDTO {
    private String message;
    private Map<String, String> details = new HashMap<>();
    private String timestamp = LocalDateTime.now().toString();

    public ErrorResponseDTO(String message, Map<String, String> details) {
        this.message = message;
        this.details = details;
    }
}
