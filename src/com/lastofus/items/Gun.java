package com.lastofus.items;

import com.lastofus.player.Item;

public class Gun extends Item {

    private int charges;
    public int damage;
    public int use;

    public Gun(int charges) {
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