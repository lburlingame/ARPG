package com.haruham.game.obj;

import com.badlogic.gdx.math.Vector3;

/**
 * Created on 10/12/2015.
 */
public class BasicCollision implements CollisionBehavior {

    private int damage;

    public BasicCollision(int damage) {
        this.damage = damage;
    }


    // maybe send an release report instead, containing information such as knockback and stuff
    public void onCollision(AttackObject attack, Character target) {
        target.takeDamage(damage);
        Vector3 attackvel = attack.getVelocity();
        target.knockback(new Vector3(attackvel.x * 2, attackvel.y * 2, 0));
    }
}
