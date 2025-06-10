package com.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "trades")
public class Trade {
    
    @Id
    private String id;
    private String fromPlayerId;
    private String toPlayerId;
    private String resourceType; // "wood", "stone", "food"
    private Integer amount;
    private String status = "PENDING"; // "PENDING", "COMPLETED", "CANCELLED"
    private LocalDateTime createdAt;
    
    // Konstruktory
    public Trade() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Trade(String fromPlayerId, String toPlayerId, String resourceType, Integer amount) {
        this.fromPlayerId = fromPlayerId;
        this.toPlayerId = toPlayerId;
        this.resourceType = resourceType;
        this.amount = amount;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }
    
    // Gettery i settery
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getFromPlayerId() { return fromPlayerId; }
    public void setFromPlayerId(String fromPlayerId) { this.fromPlayerId = fromPlayerId; }
    
    public String getToPlayerId() { return toPlayerId; }
    public void setToPlayerId(String toPlayerId) { this.toPlayerId = toPlayerId; }
    
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}