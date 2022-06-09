package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.services.dtos.CoachContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoachContractMapper {
    CoachContractMapper INSTANCE = Mappers.getMapper(CoachContractMapper.class);
    @Mapping(target = "typeOfContract",expression = "java(coachContract.getTypeOfContract().toString())")
    @Mapping(target = "coachName",source = "coach.name")
    @Mapping(target = "teamName",source = "team.name")
    CoachContractDTO toDTO(CoachContract coachContract);
    List<CoachContractDTO> toDTOs (List<CoachContract> coachContracts);
}
