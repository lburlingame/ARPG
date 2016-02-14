package com.haruham.game.input;

import com.haruham.game.util.Util;

/**
 * Created on 12/30/2015.
 */
public class ProgramInput {

    public ProgramInput() {
    }


    public void update() {
        if (Inputs.isPressed(Inputs.M)) {
            Util.saveScreenshot();
        }
    }
}
