package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.services.dtos.PlayerWithTeamDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> findAll();
    Optional<Player> findByID(Integer id);
    Player save(Player player);
    void deleteByID(Integer id);
    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);
    List<Player> findByFirstNameLike(String firstName);
    List<PlayerWithTeamDTO> findPlayerThatPlayForThatTeamAtThatYear(Integer inputYear, String teamName );
    List<Player> findByFirstNameLikeAndLastNameLike(String firstName,String lastName);
    List<Player> findByPositionAndTeamName(Position position,String teamName);
}
