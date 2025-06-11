package com.game.service;

import com.game.model.Building;
import com.game.model.BuildingType;
import com.game.model.Player;
import com.game.model.Village;
import com.game.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VillageService {
    
    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private PlayerService playerService;

    public Village createVillage(String name, String playerId) {
        Village village = new Village(name, playerId);
        return villageRepository.save(village);
    }

    public List<Village> getAllVillages() {
        return villageRepository.findAll();
    }

    public List<Village> getVillagesByPlayerId(String playerId) {
        return villageRepository.findByPlayerId(playerId);
    }

    public Village getVillageById(String id) {
        return villageRepository.findById(id).orElse(null);
    }

    public Village increasePopulation(String villageId, Integer amount) {
        Village village = villageRepository.findById(villageId).orElse(null);
        Player player;
        if (village != null) {
            player = playerService.getPlayerById(village.getPlayerId());
            if (player != null)
            {
                village.setPopulation(village.getPopulation() + amount);
                player.setFood(player.getFood() - amount);
                return villageRepository.save(village);
            }

        }
        return null;
    }

    public Village recruitWarriors(String villageId, Integer amount) {
        Village village = villageRepository.findById(villageId).orElse(null);
        Player player;
        if (village != null) {
            if (village.getPopulation() < amount) //cant recruit that many!
            {
                return null;
            }
            player = playerService.getPlayerById(village.getPlayerId());
            if (player != null)
            {
                if (player.getStone() < amount || player.getWood() < amount) //cant recruit that many!
                {
                    return null;
                }
                village.setWarriors(village.getWarriors() + amount);
                village.setPopulation(village.getPopulation() - amount);
                player.setFood(player.getStone() - amount);
                player.setFood(player.getWood() - amount);
                return villageRepository.save(village);
            }

        }
        return null;
    }
    
    public Integer getBuildingsDefensePower(String villageId)
    {
        Village village = villageRepository.findById(villageId).orElse(null);
        if (village != null)
        {
            List<Building> buildings =  village.getBuildings();
            int totalDefensePower = 0;

            for (Building building : buildings) {
                totalDefensePower += building.getType().getDefensePoints() * building.getCurrentAmount();
            }

            totalDefensePower += village.getPopulation() + (village.getWarriors() * 2);
            return totalDefensePower;
        }
        return null;
    }


    public Village buildBuilding(String villageId, Integer amount, BuildingType type) {
        Village village = villageRepository.findById(villageId).orElse(null);
        Player player;
        if (village != null) {
            player = playerService.getPlayerById(village.getPlayerId());
            if (player != null) {
                int woodCost = type.getWoodCost() * amount;
                int stoneCost = type.getStoneCost() * amount;
                int foodCost = type.getFoodCost() * amount;

                if (player.getWood() < woodCost || player.getStone() < stoneCost || player.getFood() < foodCost) {
                    return null;
                }

                Building building = village.getBuildings().stream()
                        .filter(b -> b.getType() == type)
                        .findFirst()
                        .orElse(new Building(type, 0));

                building.setCurrentAmount(building.getCurrentAmount() + amount);
                if (!village.getBuildings().contains(building)) {
                    village.getBuildings().add(building);
                }

                player.setWood(player.getWood() - woodCost);
                player.setStone(player.getStone() - stoneCost);

                return villageRepository.save(village);
            }
        }
        return null;
    }

}