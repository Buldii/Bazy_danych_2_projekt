package com.game.service;

import com.game.model.Attack;
import com.game.model.Player;
import com.game.model.Village;
import com.game.repository.AttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class AttackService {

    @Autowired
    private AttackRepository attackRepository;

    @Autowired
    private VillageService villageService;

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private PlayerService playerService;

    private final Random random = new Random();

    public Attack attackVillage(String attackerPlayerId, String defenderPlayerId,
                                String attackerVillageId, String defenderVillageId,
                                Integer attackPower) {

        Village attackerVillage = villageService.getVillageById(attackerVillageId);
        Village defenderVillage = villageService.getVillageById(defenderVillageId);

        Player attackerPlayer = playerService.getPlayerById(attackerPlayerId);
        Player defenderPlayer = playerService.getPlayerById(defenderPlayerId);

        if (attackerVillage == null || defenderVillage == null) {
            return null;
        }

        if (attackerPlayer == null || defenderPlayer == null) {
            return null;
        }

        Integer defensePower = villageService.getBuildingsDefensePower(defenderVillageId);

        Attack attack = new Attack(attackerPlayerId, defenderPlayerId,
                attackerVillageId, defenderVillageId, attackPower);
        attack.setDefensePower(defensePower);

        String winner;
        Map<String, Integer> stolenResources = null;
        if (attackPower > defensePower) {
            winner = "ATTACKER";
            stolenResources = performVictoriousAttack(defenderPlayer, attackerPlayer);
        } else {
            winner = "DEFENDER";
        }

        attack.setWinner(winner);
        Attack savedAttack = attackRepository.save(attack);


        String message = String.format("Atak gracza %s na gracza %s. Zwycięzca: %s",
                attackerPlayerId, defenderPlayerId, winner);
        String details = String.format("Siła ataku: %d, Siła obrony: %d, Zrabowane: drewno=%d, kamień=%d, jedzenie=%d",
                attackPower, defensePower,
                stolenResources.get("wood"), stolenResources.get("stone"), stolenResources.get("food"));

        eventLogService.addAttackLog(savedAttack.getId(), attackerPlayerId, defenderPlayerId, message, details);

        return savedAttack;
    }

    private Map<String, Integer> performVictoriousAttack(Player defenderPlayer, Player attackerPlayer) {
        Integer stolenWood = defenderPlayer.getWood() / 3;
        Integer stolenStone = defenderPlayer.getStone() / 3;
        Integer stolenFood = defenderPlayer.getFood() / 3;

        playerService.updatePlayerResources(defenderPlayer.getId(), -stolenWood, -stolenStone, -stolenFood);
        playerService.updatePlayerResources(attackerPlayer.getId(), stolenWood, stolenStone, stolenFood);
        return Map.of(
                "wood", stolenWood,
                "stone", stolenStone,
                "food", stolenFood
        );
    }

    public List<Attack> getAllAttacks() {
        return attackRepository.findAll();
    }

    public List<Attack> getAttacksByPlayer(String playerId) {
        List<Attack> attacks = attackRepository.findByAttackerPlayerId(playerId);
        attacks.addAll(attackRepository.findByDefenderPlayerId(playerId));
        return attacks;
    }

    public List<Attack> getAttacksByVillage(String villageId) {
        List<Attack> attacks = attackRepository.findByAttackerVillageId(villageId);
        attacks.addAll(attackRepository.findByDefenderVillageId(villageId));
        return attacks;
    }

    public Attack getAttackById(String id) {
        return attackRepository.findById(id).orElse(null);
    }
}