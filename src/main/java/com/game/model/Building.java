package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "buildings")
public class Building {

    @Id
    private String id;
    private BuildingType type;
    private String currentAmount;
    public Building() {}

    public Building(BuildingType type, String currentAmount) {
        this.type = type;
        this.currentAmount = currentAmount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public BuildingType getType() { return type; }
    public void setType(BuildingType type) { this.type = type; }

    public String getCurrentAmount() { return currentAmount; }
    public void setCurrentAmount(String currentAmount) { this.currentAmount = currentAmount; }

}
