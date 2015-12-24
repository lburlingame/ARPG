package com.patts.game.input;

import com.patts.game.obj.Character;

/**
 * Created on 3/26/2015.
 */
public abstract class InputComponent {

    protected com.patts.game.obj.Character character;

    public InputComponent() {
    }

    public abstract void update(float delta);
    public void setCharacter(Character character) {
        this.character = character;
    }

}
