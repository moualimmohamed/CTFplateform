package com.ctf.v1.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Competition;
import com.ctf.v1.repository.CompetitionRepository;

@Service
public class CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Competition getCompetitionById(UUID competitionId) {
        return competitionRepository.findById(competitionId).orElse(null);
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public void deleteCompetition(UUID competitionId) {
        competitionRepository.deleteById(competitionId);
    }
}


