package com.game.controller;

import com.game.model.Player;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;
    
    // Pobierz wszystkich graczy
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    
    // Pobierz gracza po ID
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }
    
    // Utwórz nowego gracza
    @PostMapping
    public Player createPlayer(@RequestParam String username, @RequestParam String email) {
        return playerService.createPlayer(username, email);
    }
    
    // Zaktualizuj punkty gracza
    @PutMapping("/{id}/points")
    public Player updatePoints(@PathVariable String id, @RequestParam Integer points) {
        return playerService.updatePlayerPoints(id, points);
    }
    
    // Znajdź gracza po nazwie użytkownika
    @GetMapping("/username/{username}")
    public Player getPlayerByUsername(@PathVariable String username) {
        return playerService.getPlayerByUsername(username);
    }
}