package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.lang.*;
import java.util.ArrayList;

/**
 * Created on 9/18/2015.
 */

// TODO object pooling
// TODO figure out how to add release to level's arraylist
// TODO multiple types of spells, using strategy?
// laser spell would use a rectangle that is rotated based on mouse location, and is drawn using shaders
public class AttackObject extends GameObject {

    protected Character owner;
    protected AttackType type;
    protected CollisionBehavior collision;
    protected float duration;

    protected boolean active;
    protected float stun;

    protected float knockback;
    protected ArrayList<Integer> hitids;

    protected String name;

    protected Vector3 target;
    private int damage;


    public AttackObject(Character owner, AttackType type, CollisionBehavior collision, Vector3 target, int damage) {
        this.owner = owner;
        this.type = type;
        this.collision = collision;
        this.pos = owner.getPosition();
        pos.z = 10;
        pos.y-=1;
        this.target = target;
        this.duration = 3;  // .25f
        this.damage = damage;
        vel = new Vector3(0,0,0);
       // this.pos = new Vector3(target.x, target.y, target.z);

        //this.name = name;
        hitids = new ArrayList<Integer>(); // add casters onCollision id to this;
        hitids.add(owner.getUID());
        type.init(this);
    }

    public boolean hasCollided(Character character) {
        return hitids.contains(character.getUID());
    }

    public void onCollision(Character character) {
        hitids.add(character.getUID());
        collision.onCollision(this, character);
    }

    public boolean collidesWith(Character character) {
        return type.collidesWith(this, character);
    } // could be void, and instead of returning boolean that it has collided, it just straight away calls the collision.oncollision, observers will take care of the sound

    public void update(float delta) {
        type.update(this, delta);
        if (duration > 0) {
            duration = duration - delta;
        }
    }

    public void die() {

    }

    public void draw(SpriteBatch batch) {
        type.draw(this, batch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        type.drawDebug(this, shapeRenderer);
    }

    public Vector3 getTarget() {
        return target;
    }

    public boolean isActive() {
        return duration > 0;
    }

    public int getDamage() {
        return damage;
    }
}
