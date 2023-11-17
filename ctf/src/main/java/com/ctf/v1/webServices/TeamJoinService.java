package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.TeamJoinException;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.Team;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.TeamService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamJoinService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    public void joinTeam(Long playerId, Long teamId, String joinCode) throws TeamJoinException {
        try {
        Player player = playerService.getPlayerById(playerId);
        Team team = teamService.getTeamById(teamId);

        if (player == null || team == null) {
            throw new EntityNotFoundException("Player or Team not found");
        }

        
        if (!team.isJoinCodeValid(joinCode)) {
            throw new TeamJoinException("Invalid join code");
        }

        if (team.getMembers().size() >= Team.getMaxMembers()) {
            throw new TeamJoinException("Team is full");
        }

        
        team.addMember(player);
        teamService.updateTeam(team);
    } catch (EntityNotFoundException e) {
        throw new TeamJoinException("Error joining team. " + e.getMessage());
    }
}
}


