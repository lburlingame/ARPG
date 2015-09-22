package com.haruham.game.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.entity.Collidable;
import com.haruham.game.entity.Entity;
import com.haruham.game.entity.HitCircle;
import com.haruham.game.gfx.TextureLoader;

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

    private HitCircle hit;
    private ArrayList<Integer> hitids;
    private String name;

    public Attack(Entity owner, String name, float radius, Vector3 target) {
        this.owner = owner;
        //this.pos = owner.getPosition();
        this.pos = new Vector3(target.x, target.y, target.z);
        this.dim = new Vector3(radius*2, radius*2, radius*2);
        this.name = name;
        hit = new HitCircle(new Vector3(0,0,0), radius * .6f);
        hitids = new ArrayList<>(); // add casters hit id to this;
    }

    public void reset() {

    }

    public void update(float delta) {
        pos.x += vel.x;
        pos.y += vel.y;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getSprite(1, 1), pos.x-dim.x/2, pos.y + pos.z, dim.x, dim.y);

    }

}
