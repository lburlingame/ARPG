package com.haruham.game.input;

import com.haruham.game.obj.Character;

/**
 * Created on 3/26/2015.
 */
public abstract class InputComponent {

    protected com.haruham.game.obj.Character character;

    public InputComponent() {
    }

    public abstract void update(float delta);
    public void setCharacter(Character character) {
        this.character = character;
    }

}
