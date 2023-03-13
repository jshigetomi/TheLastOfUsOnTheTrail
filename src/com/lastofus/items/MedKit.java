package com.lastofus.items;

import com.lastofus.player.Item;
import com.lastofus.player.Player;

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

    public void use(Player player) {
        if(charges > 0) {
            player.setHealth(player.getHealth() + 100);
            System.out.println("You used a medkit and gained 100 health");
            charges--;
        } else {
            System.out.println("You have no more charges");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}