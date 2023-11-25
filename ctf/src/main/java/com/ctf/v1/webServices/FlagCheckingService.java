package com.ctf.v1.webServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Challenge;
import com.ctf.v1.service.ChallengeService;

@Service
public class FlagCheckingService {
    @Autowired
    private ChallengeService challengeService;

    
    public FlagCheckingService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    public boolean checkFlag(Long challengeId, String submittedFlag) {
        Challenge challenge = challengeService.getChallengeById(challengeId);

        return challenge != null && submittedFlag.equals(challenge.getFlag());
    }
}
