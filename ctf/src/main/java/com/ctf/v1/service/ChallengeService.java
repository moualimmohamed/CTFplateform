package com.ctf.v1.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Challenge;
import com.ctf.v1.repository.ChallengeRepository;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge updateChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge getChallengeById(UUID challengeId) {
        return challengeRepository.findById(challengeId).orElse(null);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public void deleteChallenge(UUID challengeId) {
        challengeRepository.deleteById(challengeId);
    }
}


