package com.ctf.v1.webControllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.exceptions.TeamJoinException;
import com.ctf.v1.model.Team;
import com.ctf.v1.webServices.FlagCheckingService;
import com.ctf.v1.webServices.ScoringService;
import com.ctf.v1.webServices.SolvedChallengeService;
import com.ctf.v1.webServices.TeamCreationService;
import com.ctf.v1.webServices.TeamJoinService;

@RestController
@RequestMapping("/web-functionalities")
public class WebFunctionalityController {
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

    @PostMapping("/team/create")
    public Team createTeam(@RequestParam String teamName, @RequestParam Long ownerId) {
        return teamCreationService.createTeam(teamName, ownerId);
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

    

    @PostMapping("/flag/check-flag")
    public String checkFlag(@RequestParam Long playerId, @RequestParam Long challengeId, @RequestParam String submittedFlag) {
        if (flagCheckingService.checkFlag(challengeId, submittedFlag)) {
            scoringService.updateScore(playerId, challengeId);
            solvedChallengeService.markChallengeAsSolved(playerId, challengeId);
            return "Flag is correct! Score updated.";
        } else {
            
            return "Incorrect flag. Try again.";
        }
    }

    
}

