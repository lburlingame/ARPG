package com.haruham.game.item;

import com.haruham.game.entity.Entity;

/**
 * Created on 9/17/2015.
 */
public class HealingActive extends Active {

    private int amount;
    public HealingActive(int amount) {
        this.amount = amount;
    }

    public void use(Entity user) {
        user.heal(amount);
    }

    public String toString() {
        return "Heals user for " + amount + " health.";
    }
}
