package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.services.dtos.PlayerContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerContractMapper {
    PlayerContractMapper INSTANCE = Mappers.getMapper(PlayerContractMapper.class);
    PlayerContractDTO toDTO(PlayerContract playerContract);
    List<PlayerContractDTO> toDTOs (List<PlayerContract> playerContracts);
}
