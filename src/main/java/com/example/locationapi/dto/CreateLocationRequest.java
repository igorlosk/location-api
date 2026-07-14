package com.example.locationapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateLocationRequest(
        @NotBlank
        String name,
        @NotBlank
        String address,
        @Min(1)
        Integer capacity,
        String description
) {
}
