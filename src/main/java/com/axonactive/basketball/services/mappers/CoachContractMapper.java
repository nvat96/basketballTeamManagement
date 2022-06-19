package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.services.dtos.CoachContractDTO;
import com.axonactive.basketball.services.dtos.CoachWithContractDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoachContractMapper {
    CoachContractMapper INSTANCE = Mappers.getMapper(CoachContractMapper.class);
    @Mapping(target = "typeOfContract",expression = "java(coachContract.getTypeOfContract().toString())")
    @Mapping(target = "coachName",expression = "java(coachContract.getCoach().getFirstName() +\"\" + coachContract.getCoach().getLastName())")
    @Mapping(target = "teamName",source = "team.name")
    CoachContractDTO toDTO(CoachContract coachContract);
    List<CoachContractDTO> toDTOs (List<CoachContract> coachContracts);
    @Mapping(target = "contractId",source = "id")
    @Mapping(target = "coachId",source = "coach.id")
    @Mapping(target = "coachName",expression = "java(coachContract.getCoach().getFirstName() +\"\" + coachContract.getCoach().getLastName())")
    @Mapping(target = "teamName",source = "team.name")
    CoachWithContractDTO toCoachWithContractDTO(CoachContract coachContract);
    List<CoachWithContractDTO> toCoachWithContractDTOs(List<CoachContract> coachContracts);
    @AfterMapping
    default void setCoachName(CoachContract coachContract, @MappingTarget CoachContractDTO target){
        target.setCoachName(coachContract.getCoach().getFirstName()+ " "+coachContract.getCoach().getFirstName());
    }
}
