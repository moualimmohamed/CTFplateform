package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.ServiceException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.Team;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.TeamService;

@Service
public class ScoringService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private TeamService teamService;

    public void updateScore(Long playerId, Long challengeId) {
        try {
            Player player = playerService.getPlayerById(playerId);
            Challenge challenge = challengeService.getChallengeById(challengeId);

            if (player != null && challenge != null) {
                Team team = player.getTeam();
                
                if (team != null) {
                    player.setScore(player.getScore() + challenge.getPrize());
                    team.setScore(team.getScore() + challenge.getPrize());
                    playerService.updatePlayer(player);
                    teamService.updateTeam(team);
                } else {
                    throw new ServiceException("Player is not a member of any team.");
                }
            } else {
                throw new ServiceException("Invalid player or challenge ID.");
            }
        } catch (Exception e) {
            throw new ServiceException("Error updating score."+ e.getMessage());
        }
    }
}
