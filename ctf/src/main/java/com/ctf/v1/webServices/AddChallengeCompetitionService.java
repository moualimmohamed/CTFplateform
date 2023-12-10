package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.AddChallengeCompetitionException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.CompetitionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddChallengeCompetitionService {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ChallengeService challengeService;

    public void addChallenge(Long challengeid, Long competitionId) {

        try {
        Challenge challenge = challengeService.getChallengeById(challengeid);
        Competition competition = competitionService.getCompetitionById(competitionId);

        if (challenge == null || competition == null) {
            throw new EntityNotFoundException("Challenge or Competition not found");
        }

        
        if (competition.getChallenges().contains(challenge)) {
            throw new AddChallengeCompetitionException("Challenge already added");
        }


        competition.addChallenge(challenge);
        competitionService.updateCompetition(competition);
        challengeService.updateChallenge(challenge);

    } catch (EntityNotFoundException e) {
        throw new AddChallengeCompetitionException("Error adding challenge " + e.getMessage());
    }
}
}



    
