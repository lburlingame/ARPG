package com.haruham.game.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.entity.Collidable;
import com.haruham.game.entity.Entity;
import com.haruham.game.entity.HitCircle;
import com.haruham.game.gfx.TextureLoader;
import com.haruham.game.util.Direction;
import com.haruham.game.util.Util;

import java.util.ArrayList;

/**
 * Created on 9/18/2015.
 */

// TODO object pooling
// TODO figure out how to add attack to level's arraylist
// TODO multiple types of spells, using strategy?
// laser spell would use a rectangle that is rotated based on mouse location, and is drawn using shaders
public class Attack extends Collidable {

    private Entity owner;
    private float duration;
    private boolean active;

    private int damage;
    private float stun;
    private float knockback;

    private ArrayList<Integer> hitids;
    private String name;

    private Vector3 target;

    public Attack(Entity owner, String name, float radius, Vector3 target) {
        this.owner = owner;
        this.pos = owner.getPosition();
        pos.z = 10;
        this.target = target;
        vel = new Vector3(0,0,0);
       // this.pos = new Vector3(target.x, target.y, target.z);
        this.dim = new Vector3(radius*2, radius*2, radius*2);
        this.name = name;
        hit = new HitCircle(new Vector3(0,0,0), radius * .6f);
        hitids = new ArrayList<>(); // add casters hit id to this;
        hitids.add(owner.getUID());
        init();
    }

    public void init() {
        Direction dir = Util.findSlope(pos.x, pos.y, target.x, target.y);

        vel.x = Util.findX(20f + 0, dir.slope) * dir.xdir;
        vel.y = dir.slope * vel.x;

        if (dir.slope == 200000 || dir.slope == -200000)
        {
            vel.y = 20f * dir.slope / Math.abs(dir.slope);
        }
    }

    public boolean hasHit(Entity entity) {
        return hitids.contains(entity.getUID());
    }

    public void hit(Entity entity) {
        hitids.add(entity.getUID());
    }

    public void reset() {

    }

    public void update(float delta) {
        pos.x += vel.x;
        pos.y += vel.y;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getSprite(2, 1), pos.x-dim.x/2, pos.y + pos.z - dim.y/2, dim.x, dim.y);
    }

}
