package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.services.dtos.StatsDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatsMapper {
    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);
    @Mapping(target = "playerName",expression = "java(stat.getPlayer().getFirstName() + \"\" + stat.getPlayer().getLastName())")
    StatsDTO toDTO(Stats stat);
    List<StatsDTO> toDTOs (List<Stats> stats);
    @AfterMapping
    default void setPlayerName(Stats stats, @MappingTarget StatsDTO target){
        target.setPlayerName(stats.getPlayer().getFirstName() + " " + stats.getPlayer().getLastName());
    }
}
