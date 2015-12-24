package com.patts.game.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.patts.game.GameApp;
import com.patts.game.state.GameStateManager;

/**
 * Created on 10/14/2015.
 */
public class OptionsInput {

    private GameStateManager gsm;
    private GameApp game;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    public OptionsInput(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
    }


    public void update() {

        if (Inputs.isPressed(Inputs.ESCAPE)) {
            gsm.popState(true);
            //gsm.leavePlay();
        }


    }}
