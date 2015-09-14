package com.haruham.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haruham.game.GameApp;
import com.haruham.game.state.GameStateManager;

/**
 * Created on 3/26/2015.
 */
public class MainMenuInput {

    private GameStateManager gsm;
    private GameApp game;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    public MainMenuInput(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
    }

    public void update() {

        if (Inputs.isPressed(Inputs.ESCAPE)) {
            if (gsm.isPlaying()) {
                gsm.pushState(GameStateManager.PLAY);
            }else{
                game.dispose();
            }
        }
    }
}



