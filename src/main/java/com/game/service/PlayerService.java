package com.game.service;

import com.game.model.Player;
import com.game.model.Village;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(String username, String email) {
        Player player = new Player(username, email);
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Player updatePlayerResources(String playerId, Integer wood, Integer stone, Integer food) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            if (wood != null) player.setWood(player.getWood() + wood);
            if (stone != null) player.setStone(player.getStone() + stone);
            if (food != null) player.setFood(player.getFood() + food);
            return playerRepository.save(player);
        }
        return null;
    }

    public Player updatePlayerExperience(String playerId, Integer experience) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            player.setExperience(player.getExperience() + experience);
            return playerRepository.save(player);
        }
        return null;
    }
}