package com.lastofus.player;

public abstract class Item {
    public abstract void use(Player player1);// make abstract
    public abstract void display();

    public abstract void menu(Player player1); // TODO: Display effects of the item and options to use the medkit or exit

}