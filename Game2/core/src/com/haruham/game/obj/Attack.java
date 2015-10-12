package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.obj.GameObject;
import com.haruham.game.obj.Character;
import com.haruham.game.obj.HitCircle;
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
public class Attack extends GameObject {

    protected Character owner;
    protected AttackType type;
    protected CollisionBehavior collision;
    protected float duration;

    protected boolean active;
    protected int damage;
    protected float stun;

    protected float knockback;
    protected ArrayList<Integer> hitids;

    protected String name;

    protected Vector3 target;


    public Attack(Character owner, AttackType type, Vector3 target, String name, int damage) {
        this.owner = owner;
        this.type = type;
        this.pos = owner.getPosition();
        pos.z = 10;
        this.target = target;
        this.damage = damage;
        this.duration = 1000;
        vel = new Vector3(0,0,0);
       // this.pos = new Vector3(target.x, target.y, target.z);
        this.name = name;
        hitids = new ArrayList<>(); // add casters hit id to this;
        hitids.add(owner.getUID());
        type.init(this);
    }

    public boolean hasHit(Character character) {
        return hitids.contains(character.getUID());
    }

    public void hit(Character character) {
        hitids.add(character.getUID());
    }

    public boolean collidesWith(Entity other) {
        return type.collidesWith(this, other);
    }

    public void update(float delta) {
        type.update(this, delta);
        if (duration > 0) {
            duration--;
        }
    }

    public void draw(SpriteBatch batch) {
        type.draw(this, batch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        type.drawDebug(this, shapeRenderer);
    }

    public void reset() {

    }


    public Vector3 getTarget() {
        return target;
    }

    public int getDamage() {
        return damage;
    }
}
