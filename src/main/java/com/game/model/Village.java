package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "villages")
public class Village {
    
    @Id
    private String id;
    private String name;
    private String playerId;
    private Integer population = 0;
    private Integer warriors = 0;
    private List<Building> buildings = new ArrayList<>();

    public Village() {}
    
    public Village(String name, String playerId) {
        this.name = name;
        this.playerId = playerId;
        this.population = 0;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    
    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }

    public Integer getWarriors() { return warriors; }
    public void setWarriors(Integer warriors) { this.warriors = warriors; }

    public List<Building> getBuildings() { return buildings;}
    public void setBuildings(List<Building> buildings) { this.buildings = buildings; }
    public void addBuilding(Building building) { this.buildings.add(building); }

}