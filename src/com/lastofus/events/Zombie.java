package com.lastofus.events;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public void display() {
        try {
            String path = "sceneArt/Zombie.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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