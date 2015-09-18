package com.haruham.game.input;

import com.haruham.game.GameApp;

/**
 * Created on 5/2/2015.
 */
public class AIInput extends InputComponent {


    public AIInput() {
    }

    public void update(float delta) {
        character.setDx(0);
        character.setDy(0);
    }

}
