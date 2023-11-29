package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Challenge;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.CompetitionService;

@Service
public class CompetitionAddChallenge {

    @Autowired
    private CompetitionService competitionService;
    
    public Competition competitionAddChallenge(Competition competition, Challenge challenge){
        //This part didn"t work [check the issue on competitionREPO]
        //Set<Challenge> challenges = competition.getChallenges();
        //if (challenges.contains(challenge)) {
        //    throw new ChallengeException("The challenge doesn't exist");
        //    }
        //else{
        //    competition.addChallenge(challenge);
        //}
        competition.addChallenge(challenge);
        return competitionService.updateCompetition(competition);
    }
}
