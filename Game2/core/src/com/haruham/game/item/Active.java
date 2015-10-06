package com.haruham.game.item;

import com.haruham.game.obj.Entity;

/**
 * Created on 9/17/2015.
 */
public abstract class Active {

    public Active() {
    }

    public abstract void use(Entity target);
    public abstract String toString();
}
