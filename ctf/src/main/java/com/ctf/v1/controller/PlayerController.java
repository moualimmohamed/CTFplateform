package com.ctf.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.model.Player;
import com.ctf.v1.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players") 
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable Long playerId) {
        return playerService.getPlayerById(playerId);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PutMapping("/update/{playerId}")
    public Player updatePlayer(@PathVariable Long playerId, @RequestBody Player player) {
        player.setId(playerId);
        return playerService.updatePlayer(player);
    }

    @DeleteMapping("/delete/{playerId}")
    public void deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
    }
}

