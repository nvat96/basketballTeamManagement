package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.repositories.StatsRepository;
import com.axonactive.basketball.services.StatsService;
import com.axonactive.basketball.services.dtos.PlayerWithStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    StatsRepository statsRepository;
    @Autowired
    PlayerServiceImpl playerService;

    @Override
    public List<Stats> findAll() {
        return statsRepository.findAll();
    }

    @Override
    public Optional<Stats> findByID(Integer id) {
        return statsRepository.findById(id);
    }

    @Override
    public Stats save(Stats stat) {
        return statsRepository.save(stat);
    }

    @Override
    public void deleteByID(Integer id) {
        statsRepository.deleteById(id);
    }

    @Override
    public List<Stats> findByPlayerId(Integer playerID) {
        return statsRepository.findByPlayerId(playerID);
    }

    @Override
    public PlayerWithStatsDTO findPlayerWithStatsInASeason(Integer playerID, Integer season) {
        Optional<Player> player = playerService.findByID(playerID);
        if (!player.isPresent())
            return null;
        else if (2010 > season || season > 2022) {
                List<Stats> stats = statsRepository.findByPlayerId(playerID);
                PlayerWithStatsDTO playerWithStatsDTO = new PlayerWithStatsDTO();
                playerWithStatsDTO.setPlayerName(player.get().getFirstName() + " " + player.get().getLastName());
                Double totalGamePlayed = 0.0;
                Double totalPoints = 0.0;
                Double totalAssists = 0.0;
                Double totalSteals = 0.0;
                Double totalBlocks = 0.0;
                Double totalRebounds = 0.0;
                Double totalThreePointerPercentages = 0.0;
                Double totalFieldGoalPercentages = 0.0;
                Double totalFreeThrowPercentages = 0.0;
                for (int i = 0; i < stats.size() - 1; i++) {
                    totalGamePlayed += stats.get(i).getGamePlayed();
                    totalPoints += stats.get(i).getPoints();
                    totalAssists += stats.get(i).getAssists();
                    totalSteals += stats.get(i).getSteals();
                    totalBlocks += stats.get(i).getBlocks();
                    totalRebounds += stats.get(i).getRebounds();
                    totalThreePointerPercentages += stats.get(i).getThreePointerPercentage();
                    totalFieldGoalPercentages += stats.get(i).getFieldGoalPercentage();
                    totalFreeThrowPercentages += stats.get(i).getFreeThrowPercentage();
                }
                playerWithStatsDTO.setSeason(stats.size() - 1);
                playerWithStatsDTO.setGamePlayed(totalGamePlayed);
                playerWithStatsDTO.setPoints(totalPoints / (stats.size() - 1));
                playerWithStatsDTO.setAssists(totalAssists / (stats.size() - 1));
                playerWithStatsDTO.setSteals(totalSteals / (stats.size() - 1));
                playerWithStatsDTO.setBlocks(totalBlocks / (stats.size() - 1));
                playerWithStatsDTO.setRebounds(totalRebounds / (stats.size() - 1));
                playerWithStatsDTO.setThreePointerPercentage(totalThreePointerPercentages / (stats.size() - 1));
                playerWithStatsDTO.setFieldGoalPercentage(totalFieldGoalPercentages / (stats.size() - 1));
                playerWithStatsDTO.setFreeThrowPercentage(totalFreeThrowPercentages / (stats.size() - 1));
                return playerWithStatsDTO;
            }
         else return statsRepository.findPlayerWithStatsInASeason(playerID, season);
    }
}