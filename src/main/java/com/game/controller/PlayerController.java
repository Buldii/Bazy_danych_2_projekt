package com.game.controller;

import com.game.model.Player;
import com.game.model.Village;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public Player createPlayer(@RequestParam String username, @RequestParam String email) {
        return playerService.createPlayer(username, email);
    }

    @PutMapping("/{id}/experience")
    public Player updateExperience(@PathVariable String id, @RequestParam Integer experience) {
        return playerService.updatePlayerExperience(id, experience);
    }

    @PutMapping("/{id}/resources")
    public Player updateResources(@PathVariable String id,
                                   @RequestParam(required = false) Integer wood,
                                   @RequestParam(required = false) Integer stone,
                                   @RequestParam(required = false) Integer food) {
        return playerService.updatePlayerResources(id, wood, stone, food);
    }

    @GetMapping("/username/{username}")
    public Player getPlayerByUsername(@PathVariable String username) {
        return playerService.getPlayerByUsername(username);
    }
}