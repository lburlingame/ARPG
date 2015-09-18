package com.haruham.game.input;

import com.haruham.game.GameApp;
import com.haruham.game.entity.Entity;

/**
 * Created on 3/26/2015.
 */
public abstract class InputComponent {

    protected Entity character;

    public InputComponent() {
    }

    public abstract void update();
    public void setCharacter(Entity character) {
        this.character = character;
    }

}
