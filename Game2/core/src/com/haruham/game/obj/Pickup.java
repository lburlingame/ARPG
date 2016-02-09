package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created on 10/5/2015.
 */

//TODO CHANGE COIN AND HEALTH TO PICKUP
public abstract class Pickup extends Entity {

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch batch);

    public abstract void pickup(Character character);
}
