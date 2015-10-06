package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created on 10/6/2015.
 */
public interface AttackType {


    public abstract boolean collidesWith(Entity other);
    public abstract void draw(SpriteBatch batch);
    public abstract void drawDebug(ShapeRenderer shapeRenderer);

}
