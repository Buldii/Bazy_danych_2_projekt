package com.game.service;

import com.game.model.Village;
import com.game.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VillageService {
    
    @Autowired
    private VillageRepository villageRepository;
    
    // Utwórz nową wioskę
    public Village createVillage(String name, String playerId) {
        Village village = new Village(name, playerId);
        return villageRepository.save(village);
    }
    
    // Znajdź wszystkie wioski
    public List<Village> getAllVillages() {
        return villageRepository.findAll();
    }
    
    // Znajdź wioski gracza
    public List<Village> getVillagesByPlayerId(String playerId) {
        return villageRepository.findByPlayerId(playerId);
    }
    
    // Znajdź wioskę po ID
    public Village getVillageById(String id) {
        return villageRepository.findById(id).orElse(null);
    }
    
    // Zaktualizuj zasoby wiosek
    public Village updateVillageResources(String villageId, Integer wood, Integer stone, Integer food) {
        Village village = villageRepository.findById(villageId).orElse(null);
        if (village != null) {
            if (wood != null) village.setWood(village.getWood() + wood);
            if (stone != null) village.setStone(village.getStone() + stone);
            if (food != null) village.setFood(village.getFood() + food);
            return villageRepository.save(village);
        }
        return null;
    }
    
    // Zwiększ populację
    public Village increasePopulation(String villageId, Integer amount) {
        Village village = villageRepository.findById(villageId).orElse(null);
        if (village != null) {
            village.setPopulation(village.getPopulation() + amount);
            return villageRepository.save(village);
        }
        return null;
    }
}