package com.haruham.game.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haruham.game.GameApp;
import com.haruham.game.level.World;
import com.haruham.game.state.GameStateManager;
import com.haruham.game.state.Play;
import com.haruham.game.util.Util;

/**
 * Created on 3/26/2015.
 */
public class PlayInput {

    private GameStateManager gsm;
    private GameApp game;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    private Play play;

    public PlayInput(GameStateManager gsm, Play play) {
        this.gsm = gsm;
        game = gsm.getGame();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
        this.play = play;
    }

    public void update() {

        if (Inputs.isPressed(Inputs.ESCAPE)) {
            gsm.pushState(GameStateManager.GAMEMENU);
            //gsm.leavePlay();
        }

        if (Inputs.isPressed(Inputs.I)) {
            game.debug = !game.debug;
        }


        if (Inputs.isPressed(Inputs.P)) {
            play.paused = !play.paused;
        }

        int amount = Inputs.amount;
        if ((camera.viewportHeight > 250 && amount < 0) || (camera.viewportHeight < 5000 && amount > 0)) {
            camera.viewportWidth += 100 * amount;
            camera.viewportHeight += 56.25 * amount;
            //camera.zoom = 1.5f;
        }

        if (Inputs.isPressed(Inputs.N)) {
            play.getWorld().getEmitter().clear();
            play.getWorld().clearAttacks();
        }

        if (Inputs.isPressed(Inputs.ONE)) {
            play.getWorld().setShader(World.ShaderSelection.Default);
        }
        if (Inputs.isPressed(Inputs.TWO)) {
            play.getWorld().setShader(World.ShaderSelection.Ambient);
        }
        if (Inputs.isPressed(Inputs.THREE)) {
            play.getWorld().setShader(World.ShaderSelection.Light);
        }
        if (Inputs.isPressed(Inputs.FOUR)) {
            play.getWorld().setShader(World.ShaderSelection.Final);

        }
        if (Inputs.isPressed(Inputs.M3)) {
            play.getWorld().lightOscillate = !play.getWorld().lightOscillate;
        }

        /*if ((camera.zoom > .5 && amount < 0) || (camera.zoom < 2 && amount > 0)) {
            camera.zoom = camera.zoom + amount * .1f;
        }*/
    }
}


