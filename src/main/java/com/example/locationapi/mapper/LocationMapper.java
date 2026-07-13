package com.example.locationapi.mapper;

import com.example.locationapi.domain.Location;
import com.example.locationapi.dto.CreateLocationRequest;
import com.example.locationapi.dto.LocationResponse;
import com.example.locationapi.entity.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationEntity toEntity (Location location);
    Location toDomain (LocationEntity location);
    LocationResponse toResponse(Location location);
    Location toDomain(CreateLocationRequest request);
}
