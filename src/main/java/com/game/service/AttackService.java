package com.game.service;

import com.game.model.Attack;
import com.game.model.Village;
import com.game.repository.AttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    private Random random = new Random();

    public Attack attackVillage(String attackerPlayerId, String defenderPlayerId, 
                               String attackerVillageId, String defenderVillageId, 
                               Integer attackPower) {

        Village attackerVillage = villageService.getVillageById(attackerVillageId);
        Village defenderVillage = villageService.getVillageById(defenderVillageId);

        if (attackerVillage == null || defenderVillage == null) {
            return null;
        }

        Integer defensePower = defenderVillage.getPopulation() + random.nextInt(50);

        Attack attack = new Attack(attackerPlayerId, defenderPlayerId, 
                                  attackerVillageId, defenderVillageId, attackPower);
        attack.setDefensePower(defensePower);

        String winner;
        if (attackPower > defensePower) {
            winner = "ATTACKER";
            performVictoriousAttack(attack, defenderVillage);
        } else {
            winner = "DEFENDER";
        }

        attack.setWinner(winner);
        Attack savedAttack = attackRepository.save(attack);

        String message = String.format("Atak gracza %s na gracza %s. Zwycięzca: %s", 
                                     attackerPlayerId, defenderPlayerId, winner);
        String details = String.format("Siła ataku: %d, Siła obrony: %d, Zrabowane: drewno=%d, kamień=%d, jedzenie=%d", 
                                     attackPower, defensePower, 
                                     attack.getStolenWood(), attack.getStolenStone(), attack.getStolenFood());

        eventLogService.addAttackLog(savedAttack.getId(), attackerPlayerId, defenderPlayerId, message, details);

        return savedAttack;
    }

    private void performVictoriousAttack(Attack attack, Village defenderVillage) {
        Integer maxSteal = 100;

        Integer stolenWood = Math.min(defenderVillage.getWood() / 3, maxSteal);
        Integer stolenStone = Math.min(defenderVillage.getStone() / 3, maxSteal);
        Integer stolenFood = Math.min(defenderVillage.getFood() / 3, maxSteal);

        villageService.updateVillageResources(defenderVillage.getId(), -stolenWood, -stolenStone, -stolenFood);

    }

    public List<Attack> getAllAttacks() {
        return attackRepository.findAll();
    }

    public List<Attack> getAttacksByPlayer(String attackerId, String defenderId) {
        List<Attack> attacks = attackRepository.findByAttackerPlayerId(attackerId);
        attacks.addAll(attackRepository.findByDefenderPlayerId(defenderId));
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