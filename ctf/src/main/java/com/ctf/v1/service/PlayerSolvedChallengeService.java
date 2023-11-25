package com.ctf.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.PlayerSolvedChallenge;
import com.ctf.v1.repository.PlayerSolvedChallengeRepository;
import java.util.List;

@Service
public class PlayerSolvedChallengeService {
    @Autowired
    private PlayerSolvedChallengeRepository playerSolvedChallengeRepository;


    public List<PlayerSolvedChallenge> getAllPlayerSolvedChallenges() {
        return playerSolvedChallengeRepository.findAll();
    }

    public PlayerSolvedChallenge getPlayerSolvedChallengeById(Long id) {
        return playerSolvedChallengeRepository.findById(id).orElse(null);
    }

    public PlayerSolvedChallenge createPlayerSolvedChallenge(PlayerSolvedChallenge playerSolvedChallenge) {
        return playerSolvedChallengeRepository.save(playerSolvedChallenge);
    }

    public void deletePlayerSolvedChallenge(Long id) {
        playerSolvedChallengeRepository.deleteById(id);
    }

    
}

