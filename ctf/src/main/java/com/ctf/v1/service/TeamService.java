package com.ctf.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.model.Team;
import com.ctf.v1.repository.TeamRepository;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElse(null);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
    public boolean isTeamNameExists(String teamName){
        return teamRepository.existsByName(teamName);
    }
}


