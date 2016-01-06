package com.haruham.game.input;

import com.haruham.game.obj.Character;

/**
 * Created on 3/26/2015.
 */
public interface InputComponent {

    public void update(Character character, float delta);

}
