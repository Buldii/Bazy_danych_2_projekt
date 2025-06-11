package com.game.service;

import com.game.model.EventLog;
import com.game.repository.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;

    public EventLog addAttackLog(String attackId, String attackerPlayerId, String defenderPlayerId,
                                 String message, String details) {
        EventLog log = new EventLog(attackId, attackerPlayerId, defenderPlayerId, "ATTACK", message, details);
        return eventLogRepository.save(log);
    }

    public EventLog addTradeLog(String message, String details) {
        EventLog log = new EventLog("TRADE", message, details);
        return eventLogRepository.save(log);
    }

    public List<EventLog> getAllLogs() {
        return eventLogRepository.findAll();
    }

    public List<EventLog> getLogsByType(String logType) {
        return eventLogRepository.findByLogType(logType);
    }

    public List<EventLog> getLogsByAttacker(String playerId) {
        return eventLogRepository.findByAttackerPlayerId(playerId);
    }

    public List<EventLog> getLogsByDefender(String playerId) {
        return eventLogRepository.findByDefenderPlayerId(playerId);
    }

    public List<EventLog> getLogsByAttackId(String attackId) {
        return eventLogRepository.findByAttackId(attackId);
    }
}