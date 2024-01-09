package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.CompetitionJoinException;
import com.ctf.v1.model.Competition;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.Team;
import com.ctf.v1.service.CompetitionService;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.TeamService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompetitionJoinService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CompetitionService competitionService;

    public void joinCompetition(Long playerId, Long competitionId, String joinCode) throws CompetitionJoinException {

        try {
            Player player = playerService.getPlayerById(playerId);
            Team team = teamService.getTeamById(player.getTeam().getId());
            Competition competition = competitionService.getCompetitionById(competitionId);

            if (player == null || team == null || competition == null) {
                throw new EntityNotFoundException("Player or Team or competition not found");
            }

            if (!(player.getTeamRole().equals("owner"))) {
                throw new CompetitionJoinException("You are not the team owner");
            }

            if (competition.getStatus().equals("closed")) {

            }

            if (!competition.isJoinCodeValid(joinCode)) {
            }

            if (!(team.getCompetition() == null)) {

            }

            if (competition.getTeams().size() >= Competition.getMaxTeams()) {
                throw new CompetitionJoinException("Competition is full");
            }

            team.flushScore();
            teamService.updateTeam(team);
            competition.addTeam(team);
            competitionService.updateCompetition(competition);
        } catch (EntityNotFoundException e) {
            throw new CompetitionJoinException("Error joining competition. " + e.getMessage());
        }
    }
}
