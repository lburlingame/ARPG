package com.haruham.game.obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.util.Util;

/**
 * Created on 10/6/2015.
 */
public class MeleeAttack implements AttackType {

    private HitCircle hit;
    private float radius;
    private Vector3 direction;
    private float angle;

    public MeleeAttack() {
        this.radius = 0;
        angle = 360;
        hit = new HitCircle(new Vector3(0,0,0), radius);
    }

    public void init(AttackObject attack) {
        direction = attack.getTarget().sub(attack.getPosition()); // convert the 2 points into a vector from attack position to the target position
        attack.setDx(0);
        attack.setDy(0);
    }

    public boolean collidesWith(AttackObject attack, Entity other) {
        Vector3 hitCenter = hit.getCenter();

        Vector3 opos = other.getPosition();
        HitCircle o = other.getHit();
        Vector3 oCenter = o.getCenter();

        if (Util.findSquareDistance((attack.getX() + hitCenter.x) - (opos.x + oCenter.x), (attack.getY() + hitCenter.y) - (opos.y + oCenter.y)) <= Math.pow(hit.getRadius() + o.getRadius(),2)) {
            float collisionangle = Util.getAngle(other.getPosition().sub(attack.getPosition()), direction);

            if (Math.abs(collisionangle) < angle/2) {
                return true;
            }
        }

        return false;
    }

    public void update(AttackObject attack, float delta) {
        attack.setX(attack.getX() + attack.getDx() * delta);
        attack.setY(attack.getY() + attack.getDy() * delta);
        attack.setZ(attack.getZ() + attack.getDz() * delta);
        this.radius += 12 * 60 * delta; //12;
        hit.setRadius(radius);
    }

    public void draw(AttackObject attack, SpriteBatch batch) {
        //batch.draw(TextureLoader.getSprite(2, 1), release.getX()-radius, release.getY() + release.getZ() - radius, radius * 2, radius * 2);
    }

    public void drawDebug(AttackObject attack, ShapeRenderer renderer) {
        Color prev = renderer.getColor();
        renderer.setColor(Color.RED);
        renderer.circle(attack.getX() + hit.getCenter().x, attack.getY() + hit.getCenter().y, hit.getRadius());
        renderer.setColor(prev);
    }
}
