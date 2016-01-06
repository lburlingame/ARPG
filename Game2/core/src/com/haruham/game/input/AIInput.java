package com.haruham.game.input;

import com.haruham.game.obj.*;
import com.haruham.game.obj.Character;

/**
 * Created on 5/2/2015.
 */
public class AIInput implements InputComponent {


    public AIInput() {
    }

    public void update(Character character, float delta) {
        character.setDx(0);
        character.setDy(0);
    }

}
