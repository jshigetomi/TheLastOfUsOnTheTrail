package com.lastofus.items;

public class Steak {

    private int charges;
    public int damage;  // this one doesn't make sense. Shouldn't it just be use?
    public int use;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}