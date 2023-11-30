package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.ChallengeException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.CompetitionService;

@Service
public class CompetitionAddChallenge {

    @Autowired
    private CompetitionService competitionService;
    
    public Competition competitionAddChallenge(Competition competition, Challenge challenge){
        if (competition.getChallenges().contains(challenge)) {
            throw new ChallengeException("The challenge doesn't exist");
            }
        else{
            competition.addChallenge(challenge);
        }
        return competitionService.updateCompetition(competition);
    }
}
