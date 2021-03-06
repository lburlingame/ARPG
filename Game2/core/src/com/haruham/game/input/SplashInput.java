package com.haruham.game.input;


import com.haruham.game.state.GameStateManager;

/**
 * Created on 3/26/2015.
 */
public class SplashInput {

    private GameStateManager gsm;

    public SplashInput(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public void update() {
        for (int i = 0; i < Inputs.keys.length; i++) {
            if (Inputs.keys[i] && !Inputs.isPressed(Inputs.M)) { // change to F9 or something
                gsm.setState(GameStateManager.MAINMENU);
            }
        }
    }
}



