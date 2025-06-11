package com.game.repository;

import com.game.model.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TradeRepository extends MongoRepository<Trade, String> {
    List<Trade> findByFromPlayerId(String fromPlayerId);
    List<Trade> findByToPlayerId(String toPlayerId);
}