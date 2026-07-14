package com.example.locationapi.controller;

import com.example.locationapi.domain.Location;
import com.example.locationapi.dto.CreateLocationRequest;
import com.example.locationapi.dto.LocationResponse;
import com.example.locationapi.dto.UpdateLocationRequest;
import com.example.locationapi.mapper.LocationMapper;
import com.example.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;
    private final LocationMapper mapper;

    @GetMapping
    public List<LocationResponse> getAll() {

        return service.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public LocationResponse getById(@PathVariable Long id) {

        return mapper.toResponse(
                service.getById(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationResponse create(
            @RequestBody @Valid CreateLocationRequest request) {

        Location location = mapper.toDomain(request);

        return mapper.toResponse(
                service.create(location)
        );
    }

    @PutMapping("/{id}")
    public LocationResponse update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateLocationRequest request) {

        Location location = Location.builder()
                .id(id)
                .name(request.name())
                .address(request.address())
                .capacity(request.capacity())
                .description(request.description())
                .build();

        return mapper.toResponse(
                service.update(id, location)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        service.delete(id);
    }
}
