package com.haruham.game.obj;

import com.badlogic.gdx.math.Vector3;
import com.haruham.game.util.Direction;
import com.haruham.game.util.Util;

/**
 * Created on 1/6/2016.
 */
public class KnockbackCollision implements CollisionBehavior {

    private int damage;

    public KnockbackCollision(int damage) {
        this.damage = damage;
    }


    // maybe send an release report instead, containing information such as knockback and stuff
    public void onCollision(AttackObject attack, Character target) {
        target.takeDamage(damage);

        Vector3 vel = new Vector3(0,0,0);
        Vector3 attackpos = attack.getPosition();
        Vector3 charpos = target.getPosition();
        Direction dir = Util.findSlope(attackpos.x, attackpos.y, charpos.x, charpos.y);

        vel.x = Util.findX(800, dir.slope) * dir.xdir;  ///800
        vel.y = dir.slope * vel.x;

        if (dir.slope == 200000 || dir.slope == -200000)
        {
            vel.y = 800 * dir.slope / Math.abs(dir.slope);
        }

        target.knockback(vel);
    }
}
