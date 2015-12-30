package com.haruham.game.item;

/**
 * Created on 9/17/2015.
 */
public class NullActive extends Active {

    public NullActive() {
        super(-1);
    }

    public void use(com.haruham.game.obj.Character target) {

    }

    public String toString() {
        return "";
    }
}
