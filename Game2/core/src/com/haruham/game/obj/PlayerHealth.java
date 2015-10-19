package com.haruham.game.obj;

/**
 * Created on 10/6/2015.
 */
public class PlayerHealth extends HealthComponent {

    public void takeHit(AttackObject attack) {
       /// takeDamage(attack.getDamage());
    }

    protected void die() {
        alive = false;
    }
}
