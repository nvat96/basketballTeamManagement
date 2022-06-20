package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.services.dtos.OwnerDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);
    @Mapping(target = "ownerName", expression = "java(owner.getFirstName() + \"\" + owner.getLastName())")
    OwnerDTO toDTO(Owner owner);
    List<OwnerDTO> toDTOs (List<Owner> owners);
    @AfterMapping
    default void setOnwerName(Owner owner, @MappingTarget OwnerDTO target){
        target.setOwnerName(owner.getFirstName()+ " " + owner.getLastName());
    }
}
