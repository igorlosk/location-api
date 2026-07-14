package com.example.locationapi.service;

import com.example.locationapi.domain.Location;
import com.example.locationapi.entity.LocationEntity;
import com.example.locationapi.mapper.LocationMapper;
import com.example.locationapi.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "locations")
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;
    private final LocationMapper mapper;

    @Override
    @CachePut(key = "#result.id")
    public Location create(Location location) {

        LocationEntity entity = mapper.toEntity(location);

        entity.setId(null);

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Cacheable(key = "#id")
    public Location getById(Long id) {

        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Location not found"));
    }

    @Override
    public List<Location> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @CachePut(key = "#id")
    public Location update(Long id, Location location) {

        LocationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Location not found"));

        entity.setName(location.name());
        entity.setAddress(location.address());
        entity.setCapacity(location.capacity());
        entity.setDescription(location.description());

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Long id) {

        repository.deleteById(id);
    }
}
