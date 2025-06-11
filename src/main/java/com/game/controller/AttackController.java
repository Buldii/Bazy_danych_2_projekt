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

    @GetMapping
    public List<Attack> getAllAttacks() {
        return attackService.getAllAttacks();
    }

    @GetMapping("/{id}")
    public Attack getAttackById(@PathVariable String id) {
        return attackService.getAttackById(id);
    }

    @GetMapping("/player/{playerId}")
    public List<Attack> getAttacksByPlayer(@PathVariable String playerId) {
        return attackService.getAttacksByPlayer(playerId);
    }

    @GetMapping("/village/{villageId}")
    public List<Attack> getAttacksByVillage(@PathVariable String villageId) {
        return attackService.getAttacksByVillage(villageId);
    }

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
