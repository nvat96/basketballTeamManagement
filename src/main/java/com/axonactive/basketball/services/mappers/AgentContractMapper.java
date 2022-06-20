package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.AgentContract;
import com.axonactive.basketball.services.dtos.AgentContractDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgentContractMapper {
    AgentContractMapper INSTANCE = Mappers.getMapper(AgentContractMapper.class);
    @Mapping(target = "playerName",expression = "java(agentContract.getPlayer().getFirstName() + \"\" + agentContract.getPlayer().getLastName())")
    @Mapping(target = "agentName",expression = "java(agentContract.getAgent().getFirstName() + \"\" + agentContract.getAgent().getLastName())")
    AgentContractDTO toDTO (AgentContract agentContract);
    List<AgentContractDTO> toDTOs (List<AgentContract> agentContracts);
    @AfterMapping
    default void setPlayerName(AgentContract agentContract, @MappingTarget AgentContractDTO target){
        target.setPlayerName(agentContract.getPlayer().getFirstName()+ " " + agentContract.getPlayer().getLastName());
    }
    @AfterMapping
    default void setAgentName(AgentContract agentContract, @MappingTarget AgentContractDTO target){
        target.setAgentName(agentContract.getAgent().getFirstName()+ " " + agentContract.getAgent().getLastName());
    }
}
