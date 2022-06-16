package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.OwningCertificate;
import com.axonactive.basketball.services.dtos.OwningCertificateDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OwningCertificateMapper {
    OwningCertificateMapper INSTANCE = Mappers.getMapper(OwningCertificateMapper.class);
    @Mapping(target = "teamName",source = "team.name")
    @Mapping(target = "ownerName",expression = "java(owningCertificate.getOwner().getFirstName() + \"\" + owningCertificate.getOwner().getLastName())")
    OwningCertificateDTO toDTO (OwningCertificate owningCertificate);
    List<OwningCertificateDTO> toDTOs(List<OwningCertificate> owningCertificates);

    @AfterMapping
    default void setOwnerName(OwningCertificate owningCertificate, @MappingTarget OwningCertificateDTO target){
        target.setOwnerName(owningCertificate.getOwner().getFirstName()+ " " + owningCertificate.getOwner().getLastName());
    }
}
