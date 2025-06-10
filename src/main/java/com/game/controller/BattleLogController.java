package com.game.controller;

import com.game.model.BattleLog;
import com.game.service.BattleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class BattleLogController {

    @Autowired
    private BattleLogService battleLogService;

    // Pobierz wszystkie logi
    @GetMapping
    public List<BattleLog> getAllLogs() {
        return battleLogService.getAllLogs();
    }

    // Pobierz logi według typu
    @GetMapping("/type/{logType}")
    public List<BattleLog> getLogsByType(@PathVariable String logType) {
        return battleLogService.getLogsByType(logType);
    }

    // Pobierz logi gracza (jako atakujący)
    @GetMapping("/attacker/{playerId}")
    public List<BattleLog> getLogsByAttacker(@PathVariable String playerId) {
        return battleLogService.getLogsByAttacker(playerId);
    }

    // Pobierz logi gracza (jako broniący)
    @GetMapping("/defender/{playerId}")
    public List<BattleLog> getLogsByDefender(@PathVariable String playerId) {
        return battleLogService.getLogsByDefender(playerId);
    }

    // Pobierz logi dla konkretnego ataku
    @GetMapping("/attack/{attackId}")
    public List<BattleLog> getLogsByAttackId(@PathVariable String attackId) {
        return battleLogService.getLogsByAttackId(attackId);
    }
}