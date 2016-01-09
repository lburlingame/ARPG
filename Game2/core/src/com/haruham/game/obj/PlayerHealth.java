package com.haruham.game.obj;

/**
 * Created on 10/6/2015.
 */

//TODO HANDLE KNOCKBACK IN TAKEHIT? OR IN KNOCKBACKCOLLISION? connect healthcomponent with world object
public class PlayerHealth extends HealthComponent {

    public void takeHit(AttackObject attack) {
       // takeDamage(release.getDamage());
    }

    protected void die() {
        alive = false;
    }
}
