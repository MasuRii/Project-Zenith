package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Team;
import com.g1appdev.projectzenith.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(Team team) {
        logger.info("Creating new team: {}", team.getName());
        try {
            Team savedTeam = teamRepository.save(team);
            logger.info("Team created successfully with id: {}", savedTeam.getId());
            return savedTeam;
        } catch (Exception e) {
            logger.error("Error creating team: {}", e.getMessage());
            throw new RuntimeException("Failed to create team", e);
        }
    }

    public List<Team> getAllTeams() {
        logger.info("Fetching all teams");
        try {
            List<Team> teams = teamRepository.findAll();
            logger.info("Retrieved {} teams", teams.size());
            for (Team team : teams) {
                logger.debug("Team: id={}, name={}, description={}", 
                             team.getId(), team.getName(), team.getDescription());
            }
            return teams;
        } catch (Exception e) {
            logger.error("Error fetching all teams: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch teams", e);
        }
    }
}
