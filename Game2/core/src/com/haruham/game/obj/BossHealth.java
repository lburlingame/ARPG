package com.haruham.game.obj;

/**
 * Created on 10/3/2015.
 */

// on boss death, notify observers
public class BossHealth extends HealthComponent {

    public BossHealth(Character character) {
        super(character);
    }

    public void takeHit(AttackObject attack) {

    }

    public void die() {
        alive = false;
    }



}
