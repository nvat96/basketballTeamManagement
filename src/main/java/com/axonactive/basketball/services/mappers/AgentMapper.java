package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.services.dtos.AgentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgentMapper {
    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);
    AgentDTO toDTO(Agent agent);
    List<AgentDTO> toDTOs (List<Agent> agents);
}
