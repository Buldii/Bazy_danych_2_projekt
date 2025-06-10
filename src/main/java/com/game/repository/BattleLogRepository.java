package com.game.repository;

import com.game.model.BattleLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BattleLogRepository extends MongoRepository<BattleLog, String> {

    // Znajdź logi gracza (jako atakujący)
    List<BattleLog> findByAttackerPlayerId(String playerId);

    // Znajdź logi gracza (jako broniący)
    List<BattleLog> findByDefenderPlayerId(String playerId);

    // Znajdź logi według typu
    List<BattleLog> findByLogType(String logType);

    // Znajdź logi związane z konkretnym atakiem
    List<BattleLog> findByAttackId(String attackId);
}