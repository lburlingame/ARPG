package com.haruham.game.obj;

import com.badlogic.gdx.math.Vector3;

/**
 * Created on 10/12/2015.
 */
public class BasicCollision implements CollisionBehavior {


    public BasicCollision() {

    }


    // maybe send an release report instead, containing information such as knockback and stuff
    public void onCollision(AttackObject attack, Character target) {
        target.takeHit(attack);
        /*    Vector3 attackvel = attack.getVelocity();
        target.knockback(new Vector3(attackvel.x, attackvel.y, 0));*/
    }
}
