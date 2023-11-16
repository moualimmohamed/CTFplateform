package com.ctf.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.model.Competition;
import com.ctf.v1.service.CompetitionService;

import java.util.List;

@RestController
@RequestMapping("/competitions") 
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @PostMapping
    public Competition createCompetition(@RequestBody Competition competition) {
        return competitionService.createCompetition(competition);
    }

    @GetMapping("/{competitionId}")
    public Competition getCompetition(@PathVariable Long competitionId) {
        return competitionService.getCompetitionById(competitionId);
    }

    @GetMapping
    public List<Competition> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @PutMapping("/update/{competitionId}")
    public Competition updateCompetition(@PathVariable Long competitionId, @RequestBody Competition competition) {
        competition.setId(competitionId);
        return competitionService.updateCompetition(competition);
    }

    @DeleteMapping("/delete/{competitionId}")
    public void deleteCompetition(@PathVariable Long competitionId) {
        competitionService.deleteCompetition(competitionId);
    }
}

