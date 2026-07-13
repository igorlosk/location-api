package com.example.locationapi.service;

import com.example.locationapi.domain.Location;

import java.util.List;

public interface LocationService {

    Location create(Location location);

    Location getById(Long id);

    List<Location> getAll();

    Location update(Long id, Location location);

    void delete(Long id);
}
