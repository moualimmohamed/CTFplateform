package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ctf.v1.exceptions.RemoveChallengeCompetitionException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.CompetitionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RemoveChallengeCompetitionService {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ChallengeService challengeService;

    public void removeChallenge(Long challengeid, Long competitionId) {

        try {
        Challenge challenge = challengeService.getChallengeById(challengeid);
        Competition competition = competitionService.getCompetitionById(competitionId);

        if (challenge == null || competition == null) {
            throw new EntityNotFoundException("Challenge or Competition not found");
        }

        
        if (!(competition.getChallenges().contains(challenge))) {
            throw new RemoveChallengeCompetitionException("Competition does not contain this challenge");
        }


        competition.removeChallenge(challenge);
        competitionService.updateCompetition(competition);
        challengeService.updateChallenge(challenge);

    } catch (EntityNotFoundException e) {
        throw new RemoveChallengeCompetitionException("Error removing challenge " + e.getMessage());
    }
}
}

