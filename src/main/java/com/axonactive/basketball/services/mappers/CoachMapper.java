package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.services.dtos.CoachDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoachMapper {
    CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);
    @Mapping(target = "coachName",expression = "java(coach.getFirstName() + \"\" + coach.getLastName())")
    CoachDTO toDTO (Coach coach);
    List<CoachDTO> toDTOs (List<Coach> coaches);
    @AfterMapping
    default void setCoachName(Coach coach, @MappingTarget CoachDTO target){
        target.setCoachName(coach.getFirstName()+" " + coach.getLastName());
    }
}
