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
public class MeleeAttack implements AttackType {

    private HitCircle hit;
    private float radius;
    private float direction;
    private float angle;

    public MeleeAttack() {
        this.radius = 48;
        angle = 45;
        hit = new HitCircle(new Vector3(0,0,0), radius);
    }

    public void init(Attack attack) {
        Vector3 target = attack.getTarget();
        direction = Util.getAngle(attack.pos, target);
        attack.setDx(0);
        attack.setDy(0);
    }


    public boolean collidesWith(Attack attack, Entity other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findDistance((attack.getX() + hitCenter.x) - (opos.x + oCenter.x), (attack.getY() + hitCenter.y) - (opos.y + oCenter.y)) <= (hit.getRadius() + o.getRadius())) {
            float collisionangle = Util.getAngle(attack.pos, other.getPosition());
            if (collisionangle < direction + angle  && collisionangle > direction - angle) {
                return true;
            }

        }

        return false;
    }

    public void update(Attack attack, float delta) {
        attack.setX(attack.getX() + attack.getDx() * delta);
        attack.setY(attack.getY() + attack.getDy() * delta);
        attack.setZ(attack.getZ() + attack.getDz() * delta);
    }

    public void draw(Attack attack, SpriteBatch batch) {
        //batch.draw(TextureLoader.getSprite(2, 1), attack.getX()-radius, attack.getY() + attack.getZ() - radius, radius * 2, radius * 2);
    }

    public void drawDebug(Attack attack, ShapeRenderer renderer) {
        renderer.circle(attack.getX() + hit.getCenter().x, attack.getY() + hit.getCenter().y, hit.getRadius());
    }
}
