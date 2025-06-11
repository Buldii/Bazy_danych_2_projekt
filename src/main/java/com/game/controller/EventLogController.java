package com.game.controller;

import com.game.model.EventLog;
import com.game.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class EventLogController {

    @Autowired
    private EventLogService eventLogService;

    @GetMapping
    public List<EventLog> getAllLogs() {
        return eventLogService.getAllLogs();
    }

    @GetMapping("/type/{logType}")
    public List<EventLog> getLogsByType(@PathVariable String logType) {
        return eventLogService.getLogsByType(logType);
    }

    @GetMapping("/attack/{playerId}")
    public List<EventLog> getLogsByPlayer(@PathVariable String playerId) {
        return eventLogService.getLogsByPlayer(playerId);
    }

    @GetMapping("/attack/{attackId}")
    public List<EventLog> getLogsByAttackId(@PathVariable String attackId) {
        return eventLogService.getLogsByAttackId(attackId);
    }
}