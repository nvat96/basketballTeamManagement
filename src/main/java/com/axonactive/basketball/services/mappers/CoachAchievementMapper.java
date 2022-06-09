package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.CoachAchievement;
import com.axonactive.basketball.services.dtos.CoachAchievementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoachAchievementMapper {
    CoachAchievementMapper INSTANCE = Mappers.getMapper(CoachAchievementMapper.class);
    CoachAchievementDTO toDTO(CoachAchievement coachAchievement);
    List<CoachAchievementDTO> toDTOs (List<CoachAchievement> coachAchievements);
}
