package com.haruham.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haruham.game.GameMain;
import com.haruham.game.entity.Entity;
import com.haruham.game.state.GameStateManager;

/**
 * Created on 3/26/2015.
 */
public class WindowInput {

    private GameStateManager gsm;
    private GameMain game;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    public WindowInput(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
    }

    public void update() {

        if (Inputs.isPressed(Inputs.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Inputs.isPressed(Inputs.I)) {
            game.debug = !game.debug;
        }

        int amount = Inputs.amount;
        if ((camera.viewportHeight > 250 && amount < 0) || (camera.viewportHeight < 1000 && amount > 0)) {
            camera.viewportWidth += 100 * amount;
            camera.viewportHeight += 56.25 * amount;
            //camera.zoom = 1.5f;
        }
        /*if ((camera.zoom > .5 && amount < 0) || (camera.zoom < 2 && amount > 0)) {
            camera.zoom = camera.zoom + amount * .1f;
        }*/
    }
}



