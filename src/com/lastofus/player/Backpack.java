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
    private final List<Item> items = new ArrayList<>();
    private int load;


    public Backpack() {
        load = 0;
    }

    public Backpack(Item... item) {
        Collections.addAll(items, item);
        load = items.size();
    }

    public int getLoad() {
        return load;
    }

    public void addItem(Item item) {
        if (items.size() < MAX_SIZE) {
            items.add(item);
            load++;
        }
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
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