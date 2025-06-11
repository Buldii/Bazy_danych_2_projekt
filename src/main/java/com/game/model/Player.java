package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
    
    @Id
    private String id;
    private String username;
    private String email;
    private Integer wood = 100;
    private Integer stone = 100;
    private Integer food = 100;
    private Integer experience = 0;

    public Player() {}
    
    public Player(String username, String email) {
        this.username = username;
        this.email = email;
        this.experience = 0;
        this.wood = 100;
        this.stone = 100;
        this.food = 100;

    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }

    public Integer getWood() { return wood; }
    public void setWood(Integer wood) { this.wood = wood; }

    public Integer getStone() { return stone; }
    public void setStone(Integer stone) { this.stone = stone; }

    public Integer getFood() { return food; }
    public void setFood(Integer food) { this.food = food; }
}