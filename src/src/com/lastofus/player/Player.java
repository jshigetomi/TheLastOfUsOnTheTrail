package com.lastofus.player;

import java.util.ArrayList;
import java.util.Collection;

class Player {

    private String name;
    private int health;
    private int attack;
    private int eat;
    private int items;
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

}