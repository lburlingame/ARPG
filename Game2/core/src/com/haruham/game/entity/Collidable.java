package com.haruham.game.entity;


import com.badlogic.gdx.math.Vector3;
import com.haruham.game.util.Util;

/**
 * Created on 8/21/2015.
 */
public abstract class Collidable {

    protected Vector3 pos;   //position
    protected Vector3 dim;   //dimensions
    protected float smult; // size multiplier
    protected HitCircle hit;
    protected Vector3 vel;

    public HitCircle getHit() {
        return hit;
    }
    public Vector3 getPosition() {
        return new Vector3(pos.x, pos.y, pos.z);
    }

    // all hit circles have an angle, if angle is 360, then it wont calculate cone stuff, if its less than 360, then it will calculate cone based collison, then there wont need to be an abstract collidewith method and it will be only in superclass
   // public abstract boolean collidesWith(Collidable other);

    public boolean collidesWith(Collidable other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((pos.x + hitCenter.x) - (opos.x + oCenter.x), (pos.y + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            return true;
        }

        return false;
    }
}
