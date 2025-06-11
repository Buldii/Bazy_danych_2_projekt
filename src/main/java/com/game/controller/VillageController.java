package com.game.controller;

import com.game.model.BuildingType;
import com.game.model.Village;
import com.game.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/villages")
public class VillageController {
    
    @Autowired
    private VillageService villageService;

    @GetMapping
    public List<Village> getAllVillages() {
        return villageService.getAllVillages();
    }

    @GetMapping("/{id}")
    public Village getVillageById(@PathVariable String id) {
        return villageService.getVillageById(id);
    }

    @GetMapping("/player/{playerId}")
    public List<Village> getVillagesByPlayerId(@PathVariable String playerId) {
        return villageService.getVillagesByPlayerId(playerId);
    }

    @PostMapping
    public Village createVillage(@RequestParam String name, @RequestParam String playerId) {
        return villageService.createVillage(name, playerId);
    }

    @PutMapping("/{id}/population")
    public Village increasePopulation(@PathVariable String id, @RequestParam Integer amount) {
        return villageService.increasePopulation(id, amount);
    }

    @PutMapping("/{id}/warriors")
    public Village recruitWarriors(@PathVariable String id, @RequestParam Integer amount) {
        return villageService.recruitWarriors(id, amount);
    }

    @PutMapping("/{id}/buildings")
    public Village buildBuilding(@PathVariable String id, @RequestParam Integer amount, BuildingType type) {
        return villageService.buildBuilding(id, amount, type);
    }
}