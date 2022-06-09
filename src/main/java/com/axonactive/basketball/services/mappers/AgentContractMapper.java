package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.AgentContract;
import com.axonactive.basketball.services.dtos.AgentContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgentContractMapper {
    AgentContractMapper INSTANCE = Mappers.getMapper(AgentContractMapper.class);
    AgentContractDTO toDTO (AgentContract agentContract);
    List<AgentContractDTO> toDTOs (List<AgentContract> agentContracts);
}
