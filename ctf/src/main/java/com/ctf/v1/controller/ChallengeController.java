package com.ctf.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.model.Challenge;
import com.ctf.v1.service.ChallengeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/challenges") 
public class ChallengeController {
    @Autowired
    private ChallengeService challengeService;

    @PostMapping
    public Challenge createChallenge(@RequestBody Challenge challenge) {
        return challengeService.createChallenge(challenge);
    }

    @GetMapping("/{challengeId}")
    public Challenge getChallenge(@PathVariable UUID challengeId) {
        return challengeService.getChallengeById(challengeId);
    }

    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @PutMapping("/update/{challengeId}")
    public Challenge updateChallenge(@PathVariable UUID challengeId, @RequestBody Challenge challenge) {
        challenge.setId(challengeId);
        return challengeService.updateChallenge(challenge);
    }

    @DeleteMapping("/delete/{challengeId}")
    public void deleteChallenge(@PathVariable UUID challengeId) {
        challengeService.deleteChallenge(challengeId);
    }
}

