package com.patts.game.obj;

/**
 * Created on 10/6/2015.
 */
public class PlayerHealth extends HealthComponent {

    public void takeHit(AttackObject attack) {
       /// takeDamage(release.getDamage());
    }

    protected void die() {
        alive = false;
    }
}
