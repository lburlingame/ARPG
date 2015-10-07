package com.haruham.game.item;

import com.haruham.game.obj.Character;

/**
 * Created on 9/17/2015.
 */
public class HealingActive extends Active {

    private int amount;
    public HealingActive(int amount) {
        super(30000);
        this.amount = amount;
    }

    public void use(Character user) {
        user.heal(amount);
    }

    public String toString() {
        return "Heals user for " + amount + " health.";
    }
}
