package com.game.service;

import com.game.model.BattleLog;
import com.game.repository.BattleLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BattleLogService {

    @Autowired
    private BattleLogRepository battleLogRepository;

    // Dodaj log ataku
    public BattleLog addAttackLog(String attackId, String attackerPlayerId, String defenderPlayerId, 
                                 String message, String details) {
        BattleLog log = new BattleLog(attackId, attackerPlayerId, defenderPlayerId, "ATTACK", message, details);
        return battleLogRepository.save(log);
    }

    // Dodaj log handlu
    public BattleLog addTradeLog(String message, String details) {
        BattleLog log = new BattleLog("TRADE", message, details);
        return battleLogRepository.save(log);
    }

    // Pobierz wszystkie logi
    public List<BattleLog> getAllLogs() {
        return battleLogRepository.findAll();
    }

    // Pobierz logi według typu
    public List<BattleLog> getLogsByType(String logType) {
        return battleLogRepository.findByLogType(logType);
    }

    // Pobierz logi gracza (jako atakujący)
    public List<BattleLog> getLogsByAttacker(String playerId) {
        return battleLogRepository.findByAttackerPlayerId(playerId);
    }

    // Pobierz logi gracza (jako broniący)
    public List<BattleLog> getLogsByDefender(String playerId) {
        return battleLogRepository.findByDefenderPlayerId(playerId);
    }

    // Pobierz logi dla konkretnego ataku
    public List<BattleLog> getLogsByAttackId(String attackId) {
        return battleLogRepository.findByAttackId(attackId);
    }
}