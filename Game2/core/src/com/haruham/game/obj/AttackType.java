package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created on 10/6/2015.
 */
public interface AttackType {

    public void init(Attack attack);
    public boolean collidesWith(Attack attack, Entity other);
    public void update(Attack attack, float delta);
    public void draw(Attack attack, SpriteBatch batch);
    public void drawDebug(Attack attack, ShapeRenderer shapeRenderer);

}
