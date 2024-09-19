package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Team;
import com.g1appdev.projectzenith.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    // Create a new team
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

    // Fetch all teams
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

    // Update an existing team
    public Team updateTeam(Long id, Team updatedTeam) {
        logger.info("Updating team with id: {}", id);
        try {
            Optional<Team> existingTeamOpt = teamRepository.findById(id);
            if (existingTeamOpt.isPresent()) {
                Team existingTeam = existingTeamOpt.get();
                existingTeam.setName(updatedTeam.getName());
                existingTeam.setDescription(updatedTeam.getDescription());
                Team savedTeam = teamRepository.save(existingTeam);
                logger.info("Team updated successfully with id: {}", savedTeam.getId());
                return savedTeam;
            } else {
                logger.error("Team not found with id: {}", id);
                throw new RuntimeException("Team not found");
            }
        } catch (Exception e) {
            logger.error("Error updating team: {}", e.getMessage());
            throw new RuntimeException("Failed to update team", e);
        }
    }

    // Delete a team
    public void deleteTeam(Long id) {
        logger.info("Deleting team with id: {}", id);
        try {
            if (teamRepository.existsById(id)) {
                teamRepository.deleteById(id);
                logger.info("Team deleted successfully with id: {}", id);
            } else {
                logger.error("Team not found with id: {}", id);
                throw new RuntimeException("Team not found");
            }
        } catch (Exception e) {
            logger.error("Error deleting team: {}", e.getMessage());
            throw new RuntimeException("Failed to delete team", e);
        }
    }
}
