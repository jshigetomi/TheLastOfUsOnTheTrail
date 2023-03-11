package com.lastofus.player;

public class Player {

    private String name;
    private int health;
    public int attack;
    public int eat;
    private int items;
    private Backpack jansport = null;
    boolean isAlive;


    public Player(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", eat=" + eat +
                ", items=" + items +
                ", jansport=" + jansport +
                ", isAlive=" + isAlive +
                '}';
    }
}