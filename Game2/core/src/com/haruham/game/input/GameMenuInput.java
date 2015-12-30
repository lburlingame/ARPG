package com.haruham.game.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haruham.game.GameApp;
import com.haruham.game.state.GameStateManager;

/**
 * Created on 3/26/2015.
 */
public class GameMenuInput {

    private GameStateManager gsm;
    private GameApp game;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    public GameMenuInput(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
    }

    public void update() {

        if (Inputs.isPressed(Inputs.ESCAPE)) {
            gsm.popState(true);
        }

        if (Inputs.isPressed(Inputs.M1)) {
            //gsm.pushState(GameStateManager.OPTIONS);
        }
        /*if ((camera.zoom > .5 && amount < 0) || (camera.zoom < 2 && amount > 0)) {
            camera.zoom = camera.zoom + amount * .1f;
        }*/
    }
}



