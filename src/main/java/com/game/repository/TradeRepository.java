package com.game.repository;

import com.game.model.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TradeRepository extends MongoRepository<Trade, String> {
    
    // Znajdź transakcje wysłane przez gracza
    List<Trade> findByFromPlayerId(String fromPlayerId);
    
    // Znajdź transakcje otrzymane przez gracza
    List<Trade> findByToPlayerId(String toPlayerId);
    
    // Znajdź transakcje według statusu
    List<Trade> findByStatus(String status);
}