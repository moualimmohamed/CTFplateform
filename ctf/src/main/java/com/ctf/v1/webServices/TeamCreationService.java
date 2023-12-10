package com.ctf.v1.webServices;

import com.ctf.v1.exceptions.TeamCreationException;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.Team;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamCreationService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Transactional
    public Team createTeam(String teamName, Long ownerId) throws TeamCreationException {
        try {
            if (teamService.isTeamNameExists(teamName)) {
                throw new TeamCreationException("Team name is already in use");
            }

            Player owner = playerService.getPlayerById(ownerId);

            if (owner.getTeam() != null) {
                throw new TeamCreationException("Player is already a member of a team");
            }

            owner.flushScore();
            playerService.updatePlayer(owner);
            Team newTeam = Team.createTeam(teamName, owner);

            return teamService.createTeam(newTeam);
        } catch (Exception e) {

            throw new TeamCreationException("Error creating team"+ e.getMessage());
        }
    }
}
