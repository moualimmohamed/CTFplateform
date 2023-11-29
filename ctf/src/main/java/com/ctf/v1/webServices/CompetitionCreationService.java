package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.CompetitionException;
import com.ctf.v1.model.Admin;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.CompetitionService;

import jakarta.transaction.Transactional;

@Service
public class CompetitionCreationService {

    @Autowired
    private CompetitionService competitionService;

    @Transactional
    public Competition createCompetition(String competitionName, Admin author){
        if(competitionService.isCompetitionNameExists(competitionName)){
            throw new CompetitionException("Competition name is already in use");
        }

        Competition newCompetition = Competition.createCompetition(competitionName, author);

        return competitionService.createCompetition(newCompetition);

    }

    
    
}
