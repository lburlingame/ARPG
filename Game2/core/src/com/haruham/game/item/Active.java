package com.haruham.game.item;

import com.haruham.game.obj.Character;

/**
 * Created on 9/17/2015.
 */
public abstract class Active {

    public Active() {
    }

    public abstract void use(Character target);
    public abstract String toString();
}
