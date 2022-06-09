package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.TeamAchievement;
import com.axonactive.basketball.services.dtos.TeamAchievementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamAchievementMapper {
    TeamAchievementMapper INSTANCE = Mappers.getMapper(TeamAchievementMapper.class);
    @Mapping(target = "teamName",source = "team.name")
    @Mapping(target = "award", expression = "java(teamAchievement.getAward().toString())")
    TeamAchievementDTO toDTO(TeamAchievement teamAchievement);
    List<TeamAchievementDTO> toDTOs (List<TeamAchievement> teamAchievements);
}
