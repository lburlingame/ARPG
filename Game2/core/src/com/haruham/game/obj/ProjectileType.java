package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.gfx.TextureLoader;
import com.haruham.game.util.Direction;
import com.haruham.game.util.Util;

/**
 * Created on 10/6/2015.
 */
public class ProjectileType implements AttackType {


    private HitCircle hit;
    private float radius;

    public ProjectileType(float radius) {
        this.radius = radius;
        hit = new HitCircle(new Vector3(0,0,0), radius * .6f);
    }

    public void init() {
        Direction dir = Util.findSlope(pos.x, pos.y, target.x, target.y);

        vel.x = Util.findX(25f + 0, dir.slope) * dir.xdir;
        vel.y = dir.slope * vel.x;

        if (dir.slope == 200000 || dir.slope == -200000)
        {
            vel.y = 25f * dir.slope / Math.abs(dir.slope);
        }
    }


    public boolean collidesWith(Entity other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((pos.x + hitCenter.x) - (opos.x + oCenter.x), (pos.y + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            return true;
        }

        return false;    }

    public void update(float delta) {
        pos.x += vel.x;
        pos.y += vel.y;
        pos.z += vel.z;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getSprite(2, 1), pos.x-dim.x/2, pos.y + pos.z - dim.z/2, dim.x, dim.z);
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(pos.x + hit.getCenter().x, pos.y + hit.getCenter().y, hit.getRadius());
    }
}
