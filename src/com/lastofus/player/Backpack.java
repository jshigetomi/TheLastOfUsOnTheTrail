package com.lastofus.player;

public class Backpack {

    private static final int MAX_SIZE = 5;
    private int inventory;
    public int load;
    public int unload;

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
}