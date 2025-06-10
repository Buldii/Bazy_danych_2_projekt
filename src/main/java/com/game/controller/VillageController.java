package com.game.controller;

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
    
    // Pobierz wszystkie wioski
    @GetMapping
    public List<Village> getAllVillages() {
        return villageService.getAllVillages();
    }
    
    // Pobierz wioskę po ID
    @GetMapping("/{id}")
    public Village getVillageById(@PathVariable String id) {
        return villageService.getVillageById(id);
    }
    
    // Pobierz wioski gracza
    @GetMapping("/player/{playerId}")
    public List<Village> getVillagesByPlayerId(@PathVariable String playerId) {
        return villageService.getVillagesByPlayerId(playerId);
    }
    
    // Utwórz nową wioskę
    @PostMapping
    public Village createVillage(@RequestParam String name, @RequestParam String playerId) {
        return villageService.createVillage(name, playerId);
    }
    
    // Zaktualizuj zasoby wiosek
    @PutMapping("/{id}/resources")
    public Village updateResources(@PathVariable String id, 
                                 @RequestParam(required = false) Integer wood,
                                 @RequestParam(required = false) Integer stone,
                                 @RequestParam(required = false) Integer food) {
        return villageService.updateVillageResources(id, wood, stone, food);
    }
    
    // Zwiększ populację
    @PutMapping("/{id}/population")
    public Village increasePopulation(@PathVariable String id, @RequestParam Integer amount) {
        return villageService.increasePopulation(id, amount);
    }
}