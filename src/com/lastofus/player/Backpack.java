package com.lastofus.player;

import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Backpack {

    private static final int MAX_SIZE = 5;
    private List<Item> items = new ArrayList<>();
    public int load;
    public int unload;

    public Backpack() {
        items.add(new MedKit(1));
        items.add(new Steak(1));
        items.add(new Gun(1));
        load = items.size();
    }

    public Backpack(Item... item) {
        Collections.addAll(items, item);
        load = items.size();
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getUnload() {
        return unload;
    }

    public void setUnload(int unload) {
        this.unload = unload;
    }

    public String viewLoad() {
        StringBuilder load = new StringBuilder("You have " + items.size() + " items in your backpack.");
        int counter = 1;
        for(Item item : items) {
            load.append(" [").append(counter).append("]: ").append(item.toString());
            counter++;
        }
        return load.toString();
    }

    public Item getItem(int index) {
        return items.get(index);
    }
}