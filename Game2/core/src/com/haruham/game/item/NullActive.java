package com.haruham.game.item;

import com.haruham.game.obj.Character;

/**
 * Created on 9/17/2015.
 */
public class NullActive extends Active {

    public NullActive() {
        super(-1);
    }

    public void use(Character target) {

    }

    public String toString() {
        return "";
    }
}
