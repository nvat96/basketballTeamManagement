package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.PlayerAchievement;
import com.axonactive.basketball.services.dtos.PlayerAchievementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerAchievementMapper {
    PlayerAchievementMapper INSTANCE = Mappers.getMapper(PlayerAchievementMapper.class);
    @Mapping(target = "award",expression = "java(playerAchievement.getAward().toString())")
    @Mapping(target = "playerName",source = "player.name")
    PlayerAchievementDTO toDTO(PlayerAchievement playerAchievement);
    List<PlayerAchievementDTO> toDTOs (List<PlayerAchievement> playerAchievements);
}
