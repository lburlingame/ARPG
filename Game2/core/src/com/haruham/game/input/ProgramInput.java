package com.haruham.game.input;

import com.haruham.game.GameApp;
import com.haruham.game.util.Util;

/**
 * Created on 12/30/2015.
 */
public class ProgramInput {

    private GameApp game;

    // remove gameapp from this later if not needed
    public ProgramInput(GameApp game) {
        this.game = game;
    }


    public void update() {
        if (Inputs.isPressed(Inputs.M)) {
            Util.saveScreenshot();
        }

        if (Inputs.isPressed(Inputs.TAB)) {
            for (int i = 0; i < 5; i++) {
                game.deltas[i] = 0;
            }
        }
    }
}
