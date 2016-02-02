package com.haruham.game.obj;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.level.World;
import com.haruham.game.util.Util;

/**
 * Created on 10/6/2015.
 */
public abstract class Entity extends GameObject {

    protected HitCircle hit;
    protected World world;

    public Entity() {

    }

    public HitCircle getHit() {
        return hit;
    }

    public boolean collidesWith(Entity other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((pos.x + hitCenter.x) - (opos.x + oCenter.x), (pos.y + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            return true;
        }

        return false;
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(pos.x + hit.getCenter().x, pos.y + hit.getCenter().y, hit.getRadius());
    }
    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }

}
