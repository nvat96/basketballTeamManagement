package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.services.dtos.ArenaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArenaMapper {
    ArenaMapper INSTANCE = Mappers.getMapper(ArenaMapper.class);
    ArenaDTO toDTO (Arena arena);
    List<ArenaDTO> toDTOs (List<Arena> arenas);
}
