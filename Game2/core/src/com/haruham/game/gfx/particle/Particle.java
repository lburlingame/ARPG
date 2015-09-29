package com.haruham.game.gfx.particle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created on 5/5/2015.
 */
public abstract class Particle {

    protected Vector3 pos;
    protected Vector3 vel;
    protected int duration;

    public Particle(Vector3 pos, Vector3 v) {
        this.pos = pos;
        this.vel = v;
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch batch);

    public int getDuration() {
        return duration;
    }

}
