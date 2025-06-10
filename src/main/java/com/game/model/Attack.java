package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "attacks")
public class Attack {

    @Id
    private String id;
    private String attackerPlayerId;
    private String defenderPlayerId;
    private String attackerVillageId;
    private String defenderVillageId;
    private Integer attackPower;
    private Integer defensePower;
    private String winner; // "ATTACKER", "DEFENDER"
    private Integer stolenWood = 0;
    private Integer stolenStone = 0;
    private Integer stolenFood = 0;
    private String status = "COMPLETED"; // "COMPLETED"
    private LocalDateTime createdAt;

    // Konstruktory
    public Attack() {
        this.createdAt = LocalDateTime.now();
    }

    public Attack(String attackerPlayerId, String defenderPlayerId, 
                  String attackerVillageId, String defenderVillageId, 
                  Integer attackPower) {
        this.attackerPlayerId = attackerPlayerId;
        this.defenderPlayerId = defenderPlayerId;
        this.attackerVillageId = attackerVillageId;
        this.defenderVillageId = defenderVillageId;
        this.attackPower = attackPower;
        this.status = "COMPLETED";
        this.createdAt = LocalDateTime.now();
    }

    // Gettery i settery
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAttackerPlayerId() { return attackerPlayerId; }
    public void setAttackerPlayerId(String attackerPlayerId) { this.attackerPlayerId = attackerPlayerId; }

    public String getDefenderPlayerId() { return defenderPlayerId; }
    public void setDefenderPlayerId(String defenderPlayerId) { this.defenderPlayerId = defenderPlayerId; }

    public String getAttackerVillageId() { return attackerVillageId; }
    public void setAttackerVillageId(String attackerVillageId) { this.attackerVillageId = attackerVillageId; }

    public String getDefenderVillageId() { return defenderVillageId; }
    public void setDefenderVillageId(String defenderVillageId) { this.defenderVillageId = defenderVillageId; }

    public Integer getAttackPower() { return attackPower; }
    public void setAttackPower(Integer attackPower) { this.attackPower = attackPower; }

    public Integer getDefensePower() { return defensePower; }
    public void setDefensePower(Integer defensePower) { this.defensePower = defensePower; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }

    public Integer getStolenWood() { return stolenWood; }
    public void setStolenWood(Integer stolenWood) { this.stolenWood = stolenWood; }

    public Integer getStolenStone() { return stolenStone; }
    public void setStolenStone(Integer stolenStone) { this.stolenStone = stolenStone; }

    public Integer getStolenFood() { return stolenFood; }
    public void setStolenFood(Integer stolenFood) { this.stolenFood = stolenFood; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}