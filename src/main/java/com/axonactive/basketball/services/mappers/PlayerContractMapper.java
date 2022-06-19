package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.services.dtos.PlayerContractDTO;
import com.axonactive.basketball.services.dtos.PlayerWithContractDTO;
import com.axonactive.basketball.services.dtos.PlayerWithTeamDTO;
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
    @Mapping(target = "fullName",expression = "java(playerContract.getPlayer().getFirstName() +\"\" +playerContract.getPlayer().getLastName())")
    @Mapping(target = "teamName",source = "team.name")
    PlayerWithTeamDTO toPlayerWithTeamDTO (PlayerContract playerContract);
    List<PlayerWithTeamDTO> toPlayerWithTeamDTOs(List<PlayerContract> playerContracts);
    @Mapping(target = "fullName",expression = "java(playerContract.getPlayer().getFirstName() +\"\" +playerContract.getPlayer().getLastName())")
    @Mapping(target = "teamName",source = "team.name")
    @Mapping(target = "contractId",source = "id")
    @Mapping(target = "playerId",source = "player.id")
    PlayerWithContractDTO toPlayerWithContractDTO(PlayerContract playerContract);
    List<PlayerWithContractDTO> toPlayerWithContractDTOs(List<PlayerContract> playerContracts);

    @AfterMapping
    default void setPlayerName(PlayerContract playerContract, @MappingTarget PlayerContractDTO target){
        target.setPlayerName(playerContract.getPlayer().getFirstName() + " " + playerContract.getPlayer().getLastName());
    }
}
