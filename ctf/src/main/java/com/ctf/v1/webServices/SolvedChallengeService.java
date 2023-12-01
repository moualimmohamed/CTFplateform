package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Player;
import com.ctf.v1.model.PlayerSolvedChallenge;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.PlayerSolvedChallengeService;

import java.util.Date;
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
        if (!hasPlayerSolvedChallenge(playerId, challengeId) ) {
            if (!hasTeamsolvedChallenge(challengeId, playerId)){
            PlayerSolvedChallenge playerSolvedChallenge = new PlayerSolvedChallenge();
            playerSolvedChallenge.setPlayer(playerService.getPlayerById(playerId));
            playerSolvedChallenge.setChallenge(challengeService.getChallengeById(challengeId));
            playerSolvedChallenge.setSolvedDate(new Date());

            
            playerSolvedChallengeService.createPlayerSolvedChallenge(playerSolvedChallenge);

            return true;

            
        }else return false;
    }else return false;
    }

    private boolean hasPlayerSolvedChallenge(Long playerId, Long challengeId) {
        
        return playerSolvedChallengeService.hasPlayerSolvedChallenge(playerId, challengeId);
    }

    private boolean hasTeamsolvedChallenge(Long challengeId, Long playerId){
        Set<Player> teamMembers = playerService.getPlayerById(playerId).getTeam().getMembers();
        for (Player teamMember : teamMembers) {
            if (playerSolvedChallengeService.hasPlayerSolvedChallenge(teamMember.getId(), challengeId)) {
                return true; 
            }
        }
        return false;
    }

    }


