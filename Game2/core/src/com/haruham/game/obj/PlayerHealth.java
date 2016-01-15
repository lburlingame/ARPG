package com.haruham.game.obj;

import com.badlogic.gdx.math.Vector3;

/**
 * Created on 10/6/2015.
 */

//TODO HANDLE KNOCKBACK IN TAKEHIT? OR IN KNOCKBACKCOLLISION? connect healthcomponent with world object
public class PlayerHealth extends HealthComponent {

    public PlayerHealth(int health) {
        this.maxHealth = health;
        this.currHealth = health;
        alive = true;
    }

    public void takeHit(AttackObject attack, Character character) {
        takeDamage(attack.getDamage());
        character.getWorld().getEmitter().bloodSpatter(character.getPosition(), new Vector3(attack.getDx()*.2f + character.getTotalDx(), attack.getDy()*.2f + character.getTotalDy(),(float)Math.random() * 180 - 90f), 50);

    }

    protected void die() {
        alive = false;
    }
}
