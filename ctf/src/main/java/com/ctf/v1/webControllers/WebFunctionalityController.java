package com.ctf.v1.webControllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.webServices.FlagCheckingService;
import com.ctf.v1.webServices.ScoringService;
import com.ctf.v1.webServices.SolvedChallengeService;

@RestController
@RequestMapping("/web-functionalities")
public class WebFunctionalityController {
    @Autowired
    private FlagCheckingService flagCheckingService;
    private ScoringService scoringService;
    private SolvedChallengeService solvedChallengeService;

    

    @PostMapping("/check-flag")
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

