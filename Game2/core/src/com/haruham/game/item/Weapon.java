package com.haruham.game.item;

import com.badlogic.gdx.math.Vector2;
import com.haruham.game.entity.Entity;

/**
 * Created on 9/17/2015.
 */
public class Weapon extends Equipment {

    private int minDamage;
    private int maxDamage;
    private float attackTime; // time in seconds between attacks
    private float charge;
    private static final float MAX_CHARGE = 1.5f * 60;

    public Weapon(int itemID, String name, String description, int levelReq, int quantity) {
        super(itemID, name, description, levelReq, quantity);
        minDamage = 10;
        maxDamage = 15;
        attackTime = 1.5f;
        charge = 0;

    }

    public void attack(Entity user, Vector2 target) {
        
    }
}
