package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.ServiceException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.service.ChallengeService;

@Service
public class FlagCheckingService {

    @Autowired
    private ChallengeService challengeService;

    public boolean checkFlag(Long challengeId, String submittedFlag) {
        try {
            Challenge challenge = challengeService.getChallengeById(challengeId);
            return challenge != null && submittedFlag.equals(challenge.getFlag());
        } catch (Exception e) {
            throw new ServiceException("Error checking flag." + e.getMessage());
        }
    }
}
