package com.game.repository;

import com.game.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    
    // Znajdź gracza po nazwie użytkownika
    Player findByUsername(String username);
    
    // Znajdź gracza po emailu
    Player findByEmail(String email);
}