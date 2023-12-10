package com.ctf.v1.webControllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.exceptions.AddChallengeCompetitionException;
import com.ctf.v1.exceptions.CompetitionJoinException;
import com.ctf.v1.exceptions.CompetitionQuitException;
import com.ctf.v1.exceptions.CompetitionStatusChangeException;
import com.ctf.v1.exceptions.RemoveChallengeCompetitionException;
import com.ctf.v1.exceptions.ServiceException;
import com.ctf.v1.exceptions.TeamCreationException;
import com.ctf.v1.exceptions.TeamJoinException;
import com.ctf.v1.exceptions.TeamQuitException;
import com.ctf.v1.webServices.AddChallengeCompetitionService;
import com.ctf.v1.webServices.CompetitionChangeStatusService;
import com.ctf.v1.webServices.CompetitionCreationService;
import com.ctf.v1.webServices.CompetitionJoinService;
import com.ctf.v1.webServices.CompetitionQuitService;
import com.ctf.v1.webServices.FlagCheckingService;
import com.ctf.v1.webServices.RemoveChallengeCompetitionService;
import com.ctf.v1.webServices.ScoringService;
import com.ctf.v1.webServices.SolvedChallengeService;
import com.ctf.v1.webServices.TeamCreationService;
import com.ctf.v1.webServices.TeamJoinService;
import com.ctf.v1.webServices.TeamQuitService;


@RestController
@RequestMapping("/web-functionalities")
public class WebFunctionalityController {

    @Autowired
    private CompetitionCreationService competitionCreationService;
    @Autowired
    private CompetitionJoinService competitionJoinService;
    @Autowired
    private CompetitionChangeStatusService competitionChangeStatusService;
    @Autowired
    private CompetitionQuitService competitionQuitService;
    @Autowired
    private AddChallengeCompetitionService addChallengeCompetitionService;
    @Autowired
    private RemoveChallengeCompetitionService removeChallengeCompetitionService;
    @Autowired
    private FlagCheckingService flagCheckingService;
    @Autowired
    private ScoringService scoringService;
    @Autowired
    private SolvedChallengeService solvedChallengeService;
    @Autowired
    private TeamJoinService teamJoinService;
    @Autowired
    private TeamCreationService teamCreationService;
    @Autowired
    private TeamQuitService teamQuitService;

    @PostMapping("/team/create")
    public ResponseEntity<String> createTeam(
            @RequestParam String teamName,
            @RequestParam Long ownerId) {
        try {
            teamCreationService.createTeam(teamName, ownerId);
            return ResponseEntity.ok("Team created successfully ");
        } catch (TeamCreationException e) {
            return ResponseEntity.badRequest().body("Error creating team: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error occurred. Please try again later.");
        }
    }



    @PostMapping("/team/join")
    public ResponseEntity<String> joinTeam(
            @RequestParam Long playerId,
            @RequestParam Long teamId,
            @RequestParam String joinCode) {
        try {
            teamJoinService.joinTeam(playerId, teamId, joinCode);
            return ResponseEntity.ok("Successfully joined the team.");
        } catch (TeamJoinException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/team/quit")
    public ResponseEntity<String> quitTeam(
            @RequestParam Long playerId,
            @RequestParam Long teamId) {
        try {
            teamQuitService.quitTeam(playerId, teamId);
            return ResponseEntity.ok("Successfully quit the team.");
        } catch (TeamQuitException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    

    @PostMapping("/flag/check-flag")
    public ResponseEntity<String> checkFlag(
            @RequestParam Long playerId,
            @RequestParam Long challengeId,
            @RequestParam String submittedFlag) {
        try {
            if (flagCheckingService.checkFlag(challengeId, submittedFlag)) {
                if (solvedChallengeService.markChallengeAsSolved(playerId, challengeId)) {
                    scoringService.updateScore(playerId, challengeId);
                    return ResponseEntity.ok("Flag is correct! Score updated.");
                } else {
                    return ResponseEntity.badRequest().body("Challenge already solved.");
                }
            } else {
                return ResponseEntity.badRequest().body("Incorrect flag! Try again.");
            }
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Service error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error occurred. Please try again later.");
        }
    }


    
    @PostMapping("/competition/join")
    public ResponseEntity<String> joinCompetition(
            @RequestParam Long playerId,
            @RequestParam Long competitionId,
            @RequestParam String joinCode) {
        try {
            competitionJoinService.joinCompetition(playerId, competitionId, joinCode);
            return ResponseEntity.ok("Successfully joined the competition");
        } catch (CompetitionJoinException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/competition/create")
public ResponseEntity<String> createCompetition(
        @RequestParam String competitionName,
        @RequestParam Long authorId) {
    try {
        competitionCreationService.createCompetition(competitionName, authorId);
        return ResponseEntity.ok("Competition created successfully " );
    } catch (ServiceException e) {
        return ResponseEntity.badRequest().body("Error creating competition: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Unexpected error occurred. Please try again later.");
    }
}


    @PostMapping("/competition/status")
    public ResponseEntity<String> competitionChangeStatus(@RequestParam Long competitionId) {
        try {
            competitionChangeStatusService.competitionChangeStatus(competitionId);
            return ResponseEntity.ok("Successfully changed competition status");
        } catch (CompetitionStatusChangeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/competition/quit")
    public ResponseEntity<String> quitCompetition(
            @RequestParam Long playerId,
            @RequestParam Long competitionId,
            @RequestParam Long teamId) {
        try {
            competitionQuitService.quitCompetition(playerId, competitionId, teamId);
            return ResponseEntity.ok("Successfully quit the competition");
        } catch (CompetitionQuitException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/competition/add/challenge")
    public ResponseEntity<String> addChalengeCompetition(
            @RequestParam Long challengeId,
            @RequestParam Long competitionId) {
        try {
            addChallengeCompetitionService.addChallenge(challengeId, competitionId);
            return ResponseEntity.ok("Successfully added challenge to competition");
        } catch (AddChallengeCompetitionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/competition/remove/challenge")
    public ResponseEntity<String> removeChalengeCompetition(
            @RequestParam Long challengeId,
            @RequestParam Long competitionId) {
        try {
            removeChallengeCompetitionService.removeChallenge(challengeId, competitionId);
            return ResponseEntity.ok("Successfully removed challenge from competition");
        } catch (RemoveChallengeCompetitionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}

