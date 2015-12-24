package com.patts.game.obj;

/**
 * Created on 8/22/2015.
 */
public class CharacterInfo {

    public int current_health;
    public int MAX_HEALTH;

    public int current_mana;
    public int MAX_MANA;

    private int level;

    public int current_exp;
    public int NEXT_LEVEL_EXP;
    public boolean alive = true;

    public CharacterInfo() {
        current_health = 1000;
        MAX_HEALTH = 1000;
        current_mana = MAX_MANA = 1000;

    }

    public void update(float delta) {

    }


    public int getCurrHealth() {
        return current_health;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }


    public boolean isAlive() {
        return current_health > 0;
    }

    public int getLevel() {
        return level;
    }

    public boolean takeDamage(int damage) {
        current_health = current_health - damage;
        if (current_health < 0) current_health = 0;
        return isAlive();
    }

    public void heal(int amount) {
        current_health = current_health + amount;
        if (current_health > MAX_HEALTH) current_health = MAX_HEALTH;
    }

    public void die() {
        alive = false;
    }
}
