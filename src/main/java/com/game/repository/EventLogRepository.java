package com.game.repository;

import com.game.model.EventLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventLogRepository extends MongoRepository<EventLog, String> {
    List<EventLog> findByAttackerPlayerId(String playerId);
    List<EventLog> findByDefenderPlayerId(String playerId);
    List<EventLog> findByLogType(String logType);
    List<EventLog> findByAttackId(String attackId);
}