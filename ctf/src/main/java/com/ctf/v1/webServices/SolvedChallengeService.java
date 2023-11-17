package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.PlayerSolvedChallenge;
import com.ctf.v1.repository.PlayerSolvedChallengeRepository;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.PlayerService;

import java.util.Date;


@Service
public class SolvedChallengeService {
    @Autowired
    private PlayerSolvedChallengeRepository playerSolvedChallengeRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ChallengeService challengeService;



    public void markChallengeAsSolved(Long playerId, Long challengeId) {
        if (!hasPlayerSolvedChallenge(playerId, challengeId)) {
            PlayerSolvedChallenge playerSolvedChallenge = new PlayerSolvedChallenge();
            playerSolvedChallenge.setPlayer(playerService.getPlayerById(playerId));
            playerSolvedChallenge.setChallenge(challengeService.getChallengeById(challengeId));
            playerSolvedChallenge.setSolvedDate(new Date());

            
            playerSolvedChallengeRepository.save(playerSolvedChallenge);

            
        }
    }

    private boolean hasPlayerSolvedChallenge(Long playerId, Long challengeId) {
        
        return playerSolvedChallengeRepository.existsByPlayerIdAndChallengeId(playerId, challengeId);
    }
}

