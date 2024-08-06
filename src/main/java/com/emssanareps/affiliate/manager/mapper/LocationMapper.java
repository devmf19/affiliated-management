package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.dto.response.LocationResponse;
import com.emssanareps.affiliate.manager.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    Location toEntity(LocationRequest locationRequest);

    @Mapping(target = "municipality", ignore = true)
    @Mapping(target = "department", ignore = true)
    LocationResponse toResponse(Location location);

    List<LocationResponse> toResponseList(List<Location> locationList);
}
