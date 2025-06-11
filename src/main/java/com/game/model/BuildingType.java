package com.game.model;

public enum BuildingType {
    TOWN_HALL("Town Hall", 100, 50, 0, 25),
    BARRACKS("Barracks", 75, 75, 25, 100);

    private final String displayName;
    private final int woodCost;
    private final int stoneCost;
    private final int foodCost;
    private final int defensePoints;

    BuildingType(String displayName, int woodCost, int stoneCost, int foodCost, int defensePoints) {
        this.displayName = displayName;
        this.woodCost = woodCost;
        this.stoneCost = stoneCost;
        this.foodCost = foodCost;
        this.defensePoints = defensePoints;
    }

    public String getDisplayName() { return displayName; }
    public int getWoodCost() { return woodCost; }
    public int getStoneCost() { return stoneCost; }
    public int getFoodCost() { return foodCost; }
    public int getDefensePoints() { return defensePoints; }
}
