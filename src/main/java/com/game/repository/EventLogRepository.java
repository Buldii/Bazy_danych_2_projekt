package com.game.repository;

import com.game.model.EventLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventLogRepository extends MongoRepository<EventLog, String> {

    // Znajdź logi gracza (jako atakujący)
    List<EventLog> findByAttackerPlayerId(String playerId);

    // Znajdź logi gracza (jako broniący)
    List<EventLog> findByDefenderPlayerId(String playerId);

    // Znajdź logi według typu
    List<EventLog> findByLogType(String logType);

    // Znajdź logi związane z konkretnym atakiem
    List<EventLog> findByAttackId(String attackId);
}