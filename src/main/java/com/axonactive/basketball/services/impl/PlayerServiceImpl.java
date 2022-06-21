package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.repositories.PlayerRepository;
import com.axonactive.basketball.services.PlayerService;
import com.axonactive.basketball.services.dtos.PlayerWithTeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findByID(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deleteByID(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> findByFirstNameAndLastName(String firstName, String lastName) {
        return playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Player> findByFirstNameLike(String firstName) {
        return playerRepository.findByFirstNameLike(firstName);
    }

    @Override
    public List<PlayerWithTeamDTO> findPlayerThatPlayForThatTeamAtThatYear(Integer inputYear, String teamName) {
        return playerRepository.findPlayerThatPlayForThatTeamAtThatYear(inputYear, teamName);
    }

    @Override
    public List<Player> findByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        return playerRepository.findByFirstNameLikeAndLastNameLike(firstName,lastName);
    }

    @Override
    public List<Player> findByPositionAndTeamName(Position position, String teamName) {
        return playerRepository.findByPositionAndTeamName(position, teamName);
    }

    @Override
    public Player findTallestPlayerInATeam(String teamName, Integer year) {
        List<PlayerWithTeamDTO> playerWithTeamDTOs = playerRepository.findPlayerThatPlayForThatTeamAtThatYear(year,teamName);
        List<Player> players = new ArrayList<>();
        for (PlayerWithTeamDTO player: playerWithTeamDTOs) {
            String[] name = player.getPlayerName().split(" ");
            players.add(playerRepository.findByFirstNameLikeAndLastNameLike(name[0],name[1]).get(0));
        }
        players = players.stream()
                .sorted(Comparator.comparingDouble(Player::getHeight).reversed())
                .collect(Collectors.toList());
        return players.get(0);
    }
}
