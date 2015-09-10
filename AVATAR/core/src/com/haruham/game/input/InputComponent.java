package com.haruham.game.input;

import com.haruham.game.GameMain;
import com.haruham.game.entity.Entity;

/**
 * Created on 3/26/2015.
 */
public abstract class InputComponent {

    protected GameMain game;
    protected Entity character;

    public InputComponent(GameMain game) {
        this.game = game;
    }

    public abstract void update();
    public void setCharacter(Entity character) {
        this.character = character;
    }

}
