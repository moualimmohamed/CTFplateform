package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.CompetitionStatusChangeException;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.CompetitionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompetitionChangeStatusService {

    @Autowired
    private CompetitionService competitionService;

    public Competition competitionChangeStatus(Long competitionId) {
        try {
            Competition competition = competitionService.getCompetitionById(competitionId);

            if (competition == null) {
                
                throw new EntityNotFoundException("Competition not found");
            }

            competition.setStatus(competition.getStatus().equals("closed") ? "open" : "closed");

            return competitionService.updateCompetition(competition);
        } catch (EntityNotFoundException e) {
            throw new CompetitionStatusChangeException("Error changing competition status. " + e.getMessage());
        }
    }
}
