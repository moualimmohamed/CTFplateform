package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.CompetitionQuitException;
import com.ctf.v1.model.Competition;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.Team;
import com.ctf.v1.service.CompetitionService;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.TeamService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompetitionQuitService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CompetitionService competitionService;

     public void quitCompetition(Long playerId, Long teamId, Long competitionId) throws CompetitionQuitException {
        try {
        Player player = playerService.getPlayerById(playerId);
        Team team = teamService.getTeamById(teamId);
        Competition competition = competitionService.getCompetitionById(competitionId);
        
        if (player == null || team == null || competition == null) {
            throw new EntityNotFoundException("Player or Team or competition not found");
        }   

        if (!(player.getTeam().equals(team))) {
            
        }

        if (!(player.getTeamRole().equals("owner"))) {
            throw new CompetitionQuitException("You are not the team owner");
        }

        if (!(team.getCompetition().equals(competition))){

        }
        team.flushScore();
        competition.removeTeam(team);
        teamService.updateTeam(team);
        competitionService.updateCompetition(competition);
    } catch (EntityNotFoundException e) {
        throw new CompetitionQuitException("Error quitting competition. " + e.getMessage());
    }
    }
}


       

