package com.haruham.game.obj;

/**
 * Created on 9/18/2015.
 */
public abstract class CollisionBehavior {

    public CollisionBehavior() {

    }

    public abstract void onCollision(Attack attack, Character character);

}
