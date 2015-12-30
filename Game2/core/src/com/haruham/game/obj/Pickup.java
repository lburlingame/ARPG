package com.haruham.game.obj;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created on 10/5/2015.
 */
public abstract class Pickup extends Entity {

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {

    }

    public abstract void pickup(Character character);

}
