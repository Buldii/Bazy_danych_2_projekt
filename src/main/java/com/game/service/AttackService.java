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
    private BattleLogService battleLogService;

    @Autowired
    private PlayerService playerService;

    private Random random = new Random();

    // Przeprowadź atak na wioskę
    public Attack attackVillage(String attackerPlayerId, String defenderPlayerId, 
                               String attackerVillageId, String defenderVillageId, 
                               Integer attackPower) {

        // Pobierz wioski
        Village attackerVillage = villageService.getVillageById(attackerVillageId);
        Village defenderVillage = villageService.getVillageById(defenderVillageId);

        if (attackerVillage == null || defenderVillage == null) {
            return null;
        }

        // Prosta logika obrony - obrona = populacja + losowy bonus
        Integer defensePower = defenderVillage.getPopulation() + random.nextInt(50);

        // Utwórz atak
        Attack attack = new Attack(attackerPlayerId, defenderPlayerId, 
                                  attackerVillageId, defenderVillageId, attackPower);
        attack.setDefensePower(defensePower);

        // Określ zwycięzcę
        String winner;
        if (attackPower > defensePower) {
            winner = "ATTACKER";
            // Atakujący wygrywa - kradnie zasoby
            performVictoriousAttack(attack, defenderVillage);
        } else {
            winner = "DEFENDER";
            // Obrońca wygrywa - brak strat
        }

        attack.setWinner(winner);
        Attack savedAttack = attackRepository.save(attack);

        // Dodaj do logów
        String message = String.format("Atak gracza %s na gracza %s. Zwycięzca: %s", 
                                     attackerPlayerId, defenderPlayerId, winner);
        String details = String.format("Siła ataku: %d, Siła obrony: %d, Zrabowane: drewno=%d, kamień=%d, jedzenie=%d", 
                                     attackPower, defensePower, 
                                     attack.getStolenWood(), attack.getStolenStone(), attack.getStolenFood());

        battleLogService.addAttackLog(savedAttack.getId(), attackerPlayerId, defenderPlayerId, message, details);

        return savedAttack;
    }

    // Wykonaj zwycięski atak - kradnij zasoby
    private void performVictoriousAttack(Attack attack, Village defenderVillage) {
        // Prosta logika kradzieży - maksymalnie 30% zasobów
        Integer maxSteal = 100; // maksymalnie 100 jednostek każdego surowca

        Integer stolenWood = Math.min(defenderVillage.getWood() / 3, maxSteal);
        Integer stolenStone = Math.min(defenderVillage.getStone() / 3, maxSteal);
        Integer stolenFood = Math.min(defenderVillage.getFood() / 3, maxSteal);

        // Odejmij zasoby od obrońcy
        villageService.updateVillageResources(defenderVillage.getId(), -stolenWood, -stolenStone, -stolenFood);

        // Zapisz ile zostało zrabowane
        attack.setStolenWood(stolenWood);
        attack.setStolenStone(stolenStone);
        attack.setStolenFood(stolenFood);
    }

    // Pobierz wszystkie ataki
    public List<Attack> getAllAttacks() {
        return attackRepository.findAll();
    }

    // Pobierz ataki gracza (jako atakujący)
    public List<Attack> getAttacksByAttacker(String attackerId) {
        return attackRepository.findByAttackerPlayerId(attackerId);
    }

    // Pobierz ataki na gracza (jako broniący)
    public List<Attack> getAttacksByDefender(String defenderId) {
        return attackRepository.findByDefenderPlayerId(defenderId);
    }

    // Pobierz ataki wioski
    public List<Attack> getAttacksByVillage(String villageId) {
        List<Attack> attacks = attackRepository.findByAttackerVillageId(villageId);
        attacks.addAll(attackRepository.findByDefenderVillageId(villageId));
        return attacks;
    }

    // Pobierz atak po ID
    public Attack getAttackById(String id) {
        return attackRepository.findById(id).orElse(null);
    }
}