package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.CoachAchievement;
import com.axonactive.basketball.services.dtos.CoachAchievementDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoachAchievementMapper {
    CoachAchievementMapper INSTANCE = Mappers.getMapper(CoachAchievementMapper.class);
    @Mapping(target = "award",expression = "java(coachAchievement.getAward().toString())")
    @Mapping(target = "coachName",expression = "java(coachAchievement.getCoach().getFirstName() + \"\" + coachAchievement.getCoach().getLastName())")
    CoachAchievementDTO toDTO(CoachAchievement coachAchievement);
    List<CoachAchievementDTO> toDTOs (List<CoachAchievement> coachAchievements);
    @AfterMapping
    default void setCoachName(CoachAchievement coachAchievement, @MappingTarget CoachAchievementDTO target){
        target.setCoachName(coachAchievement.getCoach().getFirstName()+ " " + coachAchievement.getCoach().getLastName());
    }
}
