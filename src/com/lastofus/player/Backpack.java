package com.lastofus.player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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

    public void removeItem(int index) {
        items.remove(index);
        load--;
    }

    public int getLoad() {
        return load;
    }

    public void addItem(Item item) {
        if (items.size() < MAX_SIZE) {
            items.add(item);
            load++;
        }
        else {
            System.out.println("Your backpack is full!");
        }
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String viewLoad() {
        try {
            // read all the contents from a file and output it
            String path = "backpackArt/Backpack.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder load = new StringBuilder("You have " + items.size() + " items in your backpack.");
        int counter = 1;
        for(Item item : items) {
            load.append(" [").append(counter).append("]: ").append(item.toString());
            counter++;
        }
        load.append(" [6]: Exit");
        return load.toString();
    }

    public Item getItem(int index) {
        return items.get(index);
    }
}