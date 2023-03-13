package com.lastofus.events;

class Zombie {

    private int zHealth = 75;
    private int zAttack = 25;

    public Zombie() {
    }

    public Zombie(int health, int attack) {
        this();
        this.zAttack = attack;
        this.zHealth = health;
    }

    public void die() { zHealth = 0; }

    public int getZAttack() {
        return this.zAttack;
    }

    public int getZHealth() {
        return this.zHealth;
    }

    public int setZHealth(int health) {
        return this.zHealth = health;
    }

}