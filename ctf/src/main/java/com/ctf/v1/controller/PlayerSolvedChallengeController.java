package com.ctf.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.model.PlayerSolvedChallenge;
import com.ctf.v1.service.PlayerSolvedChallengeService;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player-solved-challenges")
public class PlayerSolvedChallengeController {
    
    @Autowired
    private PlayerSolvedChallengeService playerSolvedChallengeService;


    @GetMapping
    public List<PlayerSolvedChallenge> getAllPlayerSolvedChallenges() {
        return playerSolvedChallengeService.getAllPlayerSolvedChallenges();
    }

    @GetMapping("/{id}")
    public PlayerSolvedChallenge getPlayerSolvedChallengeById(@PathVariable UUID id) {
        return playerSolvedChallengeService.getPlayerSolvedChallengeById(id);
    }

    @PostMapping
    public PlayerSolvedChallenge createPlayerSolvedChallenge(@RequestBody PlayerSolvedChallenge playerSolvedChallenge) {
        return playerSolvedChallengeService.createPlayerSolvedChallenge(playerSolvedChallenge);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayerSolvedChallenge(@PathVariable UUID id) {
        playerSolvedChallengeService.deletePlayerSolvedChallenge(id);
    }

    
}

