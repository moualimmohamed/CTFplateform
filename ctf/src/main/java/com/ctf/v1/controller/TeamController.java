package com.ctf.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.model.Team;
import com.ctf.v1.service.TeamService;

import java.util.List;


@RestController
@RequestMapping("/teams") 
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @GetMapping("/{teamId}")
    public Team getTeam(@PathVariable Long teamId) {
        return teamService.getTeamById(teamId);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PutMapping("/update/{teamId}")
    public Team updateTeam(@PathVariable Long teamId, @RequestBody Team team) {
        team.setId(teamId);
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/delete/{teamId}")
    public void deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
    }
}

