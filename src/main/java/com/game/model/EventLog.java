package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "battle_logs")
public class BattleLog {

    @Id
    private String id;
    private String attackId;
    private String attackerPlayerId;
    private String defenderPlayerId;
    private String logType; // "ATTACK", "TRADE", "VILLAGE_CREATE"
    private String message;
    private String details;
    private LocalDateTime createdAt;

    // Konstruktory
    public BattleLog() {
        this.createdAt = LocalDateTime.now();
    }

    public BattleLog(String attackId, String attackerPlayerId, String defenderPlayerId, 
                     String logType, String message, String details) {
        this.attackId = attackId;
        this.attackerPlayerId = attackerPlayerId;
        this.defenderPlayerId = defenderPlayerId;
        this.logType = logType;
        this.message = message;
        this.details = details;
        this.createdAt = LocalDateTime.now();
    }

    // Konstruktor dla logów handlu
    public BattleLog(String logType, String message, String details) {
        this.logType = logType;
        this.message = message;
        this.details = details;
        this.createdAt = LocalDateTime.now();
    }

    // Gettery i settery
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAttackId() { return attackId; }
    public void setAttackId(String attackId) { this.attackId = attackId; }

    public String getAttackerPlayerId() { return attackerPlayerId; }
    public void setAttackerPlayerId(String attackerPlayerId) { this.attackerPlayerId = attackerPlayerId; }

    public String getDefenderPlayerId() { return defenderPlayerId; }
    public void setDefenderPlayerId(String defenderPlayerId) { this.defenderPlayerId = defenderPlayerId; }

    public String getLogType() { return logType; }
    public void setLogType(String logType) { this.logType = logType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}