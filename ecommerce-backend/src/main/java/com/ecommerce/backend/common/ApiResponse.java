package com.ecommerce.backend.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ApiResponse {
    private final boolean success;
    private final String message;

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
