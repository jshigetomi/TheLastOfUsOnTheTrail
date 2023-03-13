package com.lastofus.events;

class Zombie {

    private int zHealth = 75;
    private int zAttack = 25;
    public int die;
    public int use;

    public Zombie(int die, int use) {
        this.die = die;
        this.use = use;
    }

    public int getDie() {
        return die;
    }

    public void setDie(int die) {
        this.die = die;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}
