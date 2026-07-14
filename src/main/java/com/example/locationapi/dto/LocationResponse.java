package com.example.locationapi.dto;

public record LocationResponse(
        Long id,
        String name,
        String address,
        Integer capacity,
        String description
) {
}
