package com.lastofus.player;

public abstract class Item {

    public void use(Player player1) {
        System.out.println("This is the parent class Item use method");
    }
    public String toString() {
        return "This is the parent class Item";
    }
}