package com.haruham.game.obj;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.util.Event;

/**
 * Created on 10/6/2015.
 */

//TODO HANDLE KNOCKBACK IN TAKEHIT? OR IN KNOCKBACKCOLLISION? connect healthcomponent with world object
public class PlayerHealth extends HealthComponent {

    public PlayerHealth(Character character, int health) {
        super(character);
        this.maxHealth = health;
        this.currHealth = health;
        alive = true;
    }

    public void takeHit(AttackObject attack) {
        float percentage = takeDamage(attack.getDamage()); // takedamage returns a float, % of health taken... use this to spawn blood
        character.getWorld().getEmitter().bloodSpatter(character.getPosition(), new Vector3(attack.getDx()*.2f + character.getTotalDx(), attack.getDy()*.2f + character.getTotalDy(), MathUtils.random() * 180 - 90f), (int)(50 * percentage));

    }

    protected void die() {
        alive = false;
        character.notify(character, Event.EVENT_CHARACTER_DEATH);
    }
}
