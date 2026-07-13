package com.example.locationapi.domain;

import lombok.Builder;

@Builder
public record Location(
        Long id,
        String name,
        String address,
        Integer capacity,
        String description
) {
}
