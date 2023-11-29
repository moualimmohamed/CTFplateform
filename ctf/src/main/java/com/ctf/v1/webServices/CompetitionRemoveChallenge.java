package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctf.v1.exceptions.ChallengeException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.CompetitionService;

public class CompetitionRemoveChallenge {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ChallengeService challengeService;
    
    public Competition competitionRemoveChallenge(Competition competition, Challenge challenge){
        if(challengeService.isChallengeNameExists(challenge.getTitle())){
            competition.removeChallenge(challenge);
        }
        else {
            throw new ChallengeException("The challenge doesn't exist");
        }
        return competitionService.updateCompetition(competition);
    }
}
