package com.game.controller;

import com.game.model.Attack;
import com.game.service.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attacks")
public class AttackController {

    @Autowired
    private AttackService attackService;

    // Pobierz wszystkie ataki
    @GetMapping
    public List<Attack> getAllAttacks() {
        return attackService.getAllAttacks();
    }

    // Pobierz atak po ID
    @GetMapping("/{id}")
    public Attack getAttackById(@PathVariable String id) {
        return attackService.getAttackById(id);
    }

    // Pobierz ataki gracza (jako atakujący)
    @GetMapping("/attacker/{playerId}")
    public List<Attack> getAttacksByAttacker(@PathVariable String playerId) {
        return attackService.getAttacksByAttacker(playerId);
    }

    // Pobierz ataki na gracza (jako broniący)
    @GetMapping("/defender/{playerId}")
    public List<Attack> getAttacksByDefender(@PathVariable String playerId) {
        return attackService.getAttacksByDefender(playerId);
    }

    // Pobierz ataki związane z wioską
    @GetMapping("/village/{villageId}")
    public List<Attack> getAttacksByVillage(@PathVariable String villageId) {
        return attackService.getAttacksByVillage(villageId);
    }

    // Przeprowadź atak
    @PostMapping
    public Attack attackVillage(@RequestParam String attackerPlayerId,
                               @RequestParam String defenderPlayerId,
                               @RequestParam String attackerVillageId,
                               @RequestParam String defenderVillageId,
                               @RequestParam Integer attackPower) {
        return attackService.attackVillage(attackerPlayerId, defenderPlayerId, 
                                         attackerVillageId, defenderVillageId, attackPower);
    }
}