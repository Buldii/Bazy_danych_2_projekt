package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "villages")
public class Village {
    
    @Id
    private String id;
    private String name;
    private String playerId;
    private Integer wood = 100;
    private Integer stone = 100;
    private Integer food = 100;
    private Integer population = 0;
    
    // Konstruktory
    public Village() {}
    
    public Village(String name, String playerId) {
        this.name = name;
        this.playerId = playerId;
        this.wood = 100;
        this.stone = 100;
        this.food = 100;
        this.population = 0;
    }
    
    // Gettery i settery
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    
    public Integer getWood() { return wood; }
    public void setWood(Integer wood) { this.wood = wood; }
    
    public Integer getStone() { return stone; }
    public void setStone(Integer stone) { this.stone = stone; }
    
    public Integer getFood() { return food; }
    public void setFood(Integer food) { this.food = food; }
    
    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }
}