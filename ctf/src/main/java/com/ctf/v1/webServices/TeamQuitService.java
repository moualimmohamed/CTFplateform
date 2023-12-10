package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.TeamJoinException;
import com.ctf.v1.exceptions.TeamQuitException;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.Team;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.TeamService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamQuitService {
    
    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

     public void quitTeam(Long playerId, Long teamId) throws TeamQuitException {
        try {
        Player player = playerService.getPlayerById(playerId);
        Team team = teamService.getTeamById(teamId);

        if (player == null || team == null) {
            throw new EntityNotFoundException("Player or Team not found");
        }   

        if (!(team.getCompetition() == null)) {

        }
        
        if (!(player.getTeam().equals(team))) {
        }

        if (team.getMembers().size() == 1) {

        player.flushScore();
        team.removeMember(player);
        playerService.updatePlayer(player);
        teamService.deleteTeam(teamId);
        
        }
        player.flushScore();
        team.removeMember(player);
        playerService.updatePlayer(player);
        teamService.updateTeam(team);
    } catch (EntityNotFoundException e) {
        throw new TeamJoinException("Error quitting team. " + e.getMessage());
    }
}
}
