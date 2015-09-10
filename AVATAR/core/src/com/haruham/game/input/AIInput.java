package com.haruham.game.input;

import com.haruham.game.GameMain;

/**
 * Created on 5/2/2015.
 */
public class AIInput extends InputComponent {


    public AIInput(GameMain game) {
        super(game);
    }

    public void update() {
        character.setDx(0);
        character.setDy(0);
    }

}
