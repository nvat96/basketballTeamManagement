package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.services.dtos.PlayerContractDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerContractMapper {
    PlayerContractMapper INSTANCE = Mappers.getMapper(PlayerContractMapper.class);
    @Mapping(target = "typeOfContract",expression = "java(playerContract.getTypeOfContract().toString())")
    @Mapping(target = "position", expression = "java(playerContract.getPosition().toString())")
    @Mapping(target = "teamName",source = "team.name")
    @Mapping(target = "playerName",expression = "java(playerContract.getPlayer().getFirstName() +\"\" +playerContract.getPlayer().getLastName())")
    PlayerContractDTO toDTO(PlayerContract playerContract);
    List<PlayerContractDTO> toDTOs (List<PlayerContract> playerContracts);

    @AfterMapping
    default void setPlayerName(PlayerContract playerContract, @MappingTarget PlayerContractDTO target){
        target.setPlayerName(playerContract.getPlayer().getFirstName() + " " + playerContract.getPlayer().getLastName());
    }
}
