package com.haruham.game.item;

import com.haruham.game.entity.Entity;

/**
 * Created on 9/17/2015.
 */
public class HealingActive extends Active {

    private int amount;
    public HealingActive(int amount) {
        super("Heals user for " + amount + "health.");
        this.amount = amount;
    }

    public void use(Entity target) {

    }
}
