package com.lastofus.player;

public class Player {

    private String name;
    private int health = 100;
    public int attack = 10;
    private Backpack jansport = null;
    private boolean hasGun = false;

    public Player(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public boolean hasGun() {
        return hasGun;
    }

    public void setHasGun(boolean hasGun) {
        this.hasGun = hasGun;
    }

    public void wearBackpack (Backpack pack) {
        jansport = pack;
    }

    public Backpack getBackpack() {
        return jansport;
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
        if(health < 0) {
            health = 0;
        }
        if(health > 100) {
            health = 100;
        }
        this.health = health;
    }

    public void setAttack(int value) {
        this.attack = value;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", jansport=" + jansport +
                '}';
    }
}