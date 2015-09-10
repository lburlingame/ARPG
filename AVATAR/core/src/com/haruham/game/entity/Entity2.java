package com.haruham.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created on 5/16/2015.
 */
public abstract class Entity2 {

    protected Body body;
    protected Sprite sprite;

    protected Vector2 pos;
    protected Vector2 dim;

    public Entity2(Vector2 pos, Vector2 dim, Texture texture) {
        sprite = new Sprite(texture);
        this.pos = pos;
        this.dim = dim;
    }

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch);



}
