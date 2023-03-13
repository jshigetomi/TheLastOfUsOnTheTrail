package com.lastofus.items;

import com.lastofus.player.Item;

public class MedKit extends Item {

    private int charges;
    public  int damage = 50;
    public int use;

    public MedKit(int charges) {
        this.charges = charges;
    }

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

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}