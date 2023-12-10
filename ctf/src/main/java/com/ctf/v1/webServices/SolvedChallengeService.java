package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.ServiceException;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.PlayerSolvedChallenge;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.PlayerSolvedChallengeService;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class SolvedChallengeService {

    @Autowired
    private PlayerSolvedChallengeService playerSolvedChallengeService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChallengeService challengeService;

    public boolean markChallengeAsSolved(Long playerId, Long challengeId) {
        try {
            if (!hasPlayerSolvedChallenge(playerId, challengeId)) {
                if (!hasTeamsolvedChallenge(challengeId, playerId)) {
                    PlayerSolvedChallenge playerSolvedChallenge = new PlayerSolvedChallenge();
                    playerSolvedChallenge.setPlayer(playerService.getPlayerById(playerId));
                    playerSolvedChallenge.setChallenge(challengeService.getChallengeById(challengeId));
                    playerSolvedChallenge.setSolvedDate(LocalDateTime.now());

                    playerSolvedChallengeService.createPlayerSolvedChallenge(playerSolvedChallenge);

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new ServiceException("Error marking challenge as solved." + e.getMessage());
        }
    }

    private boolean hasPlayerSolvedChallenge(Long playerId, Long challengeId) {
        try {
            return playerSolvedChallengeService.hasPlayerSolvedChallenge(playerId, challengeId);
        } catch (Exception e) {
            throw new ServiceException("Error checking if player has solved the challenge."+ e.getMessage());
        }
    }

    private boolean hasTeamsolvedChallenge(Long challengeId, Long playerId) {
        try {
            Set<Player> teamMembers = playerService.getPlayerById(playerId).getTeam().getMembers();
            for (Player teamMember : teamMembers) {
                if (playerSolvedChallengeService.hasPlayerSolvedChallenge(teamMember.getId(), challengeId)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new ServiceException("Error checking if team has solved the challenge." + e.getMessage());
        }
    }
}
