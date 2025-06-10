package com.game.service;

import com.game.model.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    // Utwórz nowego gracza
    public Player createPlayer(String username, String email) {
        Player player = new Player(username, email);
        return playerRepository.save(player);
    }
    
    // Znajdź wszystkich graczy
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    
    // Znajdź gracza po ID
    public Player getPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
    }
    
    // Znajdź gracza po nazwie użytkownika
    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }
    
    // Zaktualizuj punkty gracza
    public Player updatePlayerPoints(String playerId, Integer points) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            player.setPoints(player.getPoints() + points);
            return playerRepository.save(player);
        }
        return null;
    }
}