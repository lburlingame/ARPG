package com.haruham.game.item;

import com.badlogic.gdx.math.Vector2;
import com.haruham.game.entity.Entity;

/**
 * Created on 9/17/2015.
 */
public class Weapon extends Equipment {

    private int minDamage;
    private int maxDamage;
    private int attackTime; // one attack every (attackTime / 60) seconds
    private float charge;
    private static final float MAX_CHARGE = 1.5f * 60;

    public Weapon(int itemID, String name, String description, int levelReq, int quantity) {
        super(itemID, name, description, levelReq, quantity);
        minDamage = 10;
        maxDamage = 15;
        attackTime = 90;
        charge = 0;

    }

    public void attack(Entity user, Vector2 target) {
        
    }
}
