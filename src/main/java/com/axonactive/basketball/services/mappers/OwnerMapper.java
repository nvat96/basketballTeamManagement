package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.services.dtos.OwnerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);
    OwnerDTO toDTO(Owner owner);
    List<OwnerDTO> toDTOs (List<Owner> owners);
}
