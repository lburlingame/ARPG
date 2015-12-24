package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.gfx.TextureLoader;
import com.haruham.game.util.Direction;
import com.haruham.game.util.Util;

/**
 * Created on 10/6/2015.
 */
public class Projectile implements AttackType {

    private HitCircle hit;
    private float radius;

    public Projectile(float radius) {
        this.radius = radius;
        hit = new HitCircle(new Vector3(0,0,0), radius * .6f);
    }

    public void init(AttackObject attack) {
        Vector3 target = attack.getTarget();
        Vector3 vel = new Vector3(0,0,0);
        Direction dir = Util.findSlope(attack.getX(), attack.getY(), target.x, target.y);

        vel.x = Util.findX(500, dir.slope) * dir.xdir;  ///800
        vel.y = dir.slope * vel.x;

        if (dir.slope == 200000 || dir.slope == -200000)
        {
            vel.y = 500 * dir.slope / Math.abs(dir.slope);
        }

        attack.setDx(vel.x);
        attack.setDy(vel.y);

    }


    public boolean collidesWith(AttackObject attack, Entity other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((attack.getX() + hitCenter.x) - (opos.x + oCenter.x), (attack.getY() + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            return true;
        }

        return false;
    }

    public void update(AttackObject attack, float delta) {
        attack.setX(attack.getX() + attack.getDx() * delta);
        attack.setY(attack.getY() + attack.getDy() * delta);
        attack.setZ(attack.getZ() + attack.getDz() * delta);
    }

    public void draw(AttackObject attack, SpriteBatch batch) {
        batch.draw(TextureLoader.getSprite(2, 1), attack.getX()-radius, attack.getY() + attack.getZ() - radius, radius * 2, radius * 2);
    }

    public void drawDebug(AttackObject attack, ShapeRenderer renderer) {
        Color prev = renderer.getColor();
        renderer.setColor(Color.RED);
        renderer.circle(attack.getX() + hit.getCenter().x, attack.getY() + hit.getCenter().y, hit.getRadius());
        renderer.setColor(prev);
    }
}
