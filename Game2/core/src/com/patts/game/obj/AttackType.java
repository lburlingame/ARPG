package com.patts.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created on 10/6/2015.
 */
public interface AttackType {

    public void init(AttackObject attack);
    public boolean collidesWith(AttackObject attack, Entity other);
    public void update(AttackObject attack, float delta);
    public void draw(AttackObject attack, SpriteBatch batch);
    public void drawDebug(AttackObject attack, ShapeRenderer shapeRenderer);

}
