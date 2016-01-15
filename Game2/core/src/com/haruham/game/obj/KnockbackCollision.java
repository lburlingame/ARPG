package com.haruham.game.obj;

import com.badlogic.gdx.math.Vector3;
import com.haruham.game.util.Direction;
import com.haruham.game.util.Util;

/**
 * Created on 1/6/2016.
 */
public class KnockbackCollision implements CollisionBehavior {


    public KnockbackCollision() {

    }


    // maybe send an release report instead, containing information such as knockback and stuff
    public void onCollision(AttackObject attack, Character target) {
        target.takeHit(attack);

        float knockback = 550; //550 is pretty good for the aoe knockback
        Vector3 vel = new Vector3(0,0,0);
        Vector3 attackpos = attack.getPosition();
        Vector3 charpos = target.getPosition();
        Direction dir = Util.findSlope(attackpos.x, attackpos.y, charpos.x, charpos.y);

        vel.x = Util.findX(knockback, dir.slope) * dir.xdir;  ///800
        vel.y = dir.slope * vel.x;

        if (dir.slope == 200000 || dir.slope == -200000)
        {
            vel.y = knockback * dir.slope / Math.abs(dir.slope);
        }

        target.knockback(vel);
    }
}
