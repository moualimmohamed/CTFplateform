package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Competition;
import com.ctf.v1.service.CompetitionService;

@Service
public class CompetitionChangeStatus {

    @Autowired
    private CompetitionService competitionService;

    public Competition competitionChangeStatus(Competition competition){
        if(competition.getStatus().equals("closed")){
            competition.setStatus("open");
        }else competition.setStatus("closed");
        return competitionService.updateCompetition(competition);
    }
    
    
}
