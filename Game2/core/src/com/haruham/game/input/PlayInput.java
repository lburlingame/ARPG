package com.haruham.game.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.GameApp;
import com.haruham.game.level.World;
import com.haruham.game.obj.*;
import com.haruham.game.obj.Character;
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
        }

        if (Inputs.isPressed(Inputs.V)) {
            play.getWorld().clearAttacks();

        }

        if (Inputs.isPressed(Inputs.TAB)) {
            play.getWorld().clearEverything();
        }

        if (Inputs.isPressed(Inputs.ONE)) {
            play.setShader(Play.ShaderSelection.Default);
        }
        if (Inputs.isPressed(Inputs.TWO)) {
            play.setShader(Play.ShaderSelection.Ambient);
        }
        if (Inputs.isPressed(Inputs.THREE)) {
            play.setShader(Play.ShaderSelection.Light);
        }
        if (Inputs.isPressed(Inputs.FOUR)) {
            play.setShader(Play.ShaderSelection.Final);

        }

        if (Inputs.isPressed(Inputs.UP)) {
            play.lightrgb[play.lightix] += .05f;
            play.updateShader();
        }
        if (Inputs.isPressed(Inputs.DOWN)) {
            play.lightrgb[play.lightix] -= .05f;
            play.updateShader();
        }
        if (Inputs.isPressed(Inputs.LEFT)) {
            play.lightix--;
            if (play.lightix < 0) {
                play.lightix = play.lightrgb.length - 1;
            }
        }
        if (Inputs.isPressed(Inputs.RIGHT)) {
            play.lightix++;
            play.lightix = play.lightix % play.lightrgb.length;
        }
        if (Inputs.isPressed(Inputs.C)) {
            for (int i = 0; i < 150; i++) {
                play.getWorld().addCharacter(new Character(play.getWorld(), 1, new NullInput(), new Vector3(play.getPlayer().getPosition().add(MathUtils.random()*500-250, MathUtils.random()*500-250,0)), MathUtils.random() * 3.5f + .35f));
            }
        }
        /*if ((camera.zoom > .5 && amount < 0) || (camera.zoom < 2 && amount > 0)) {
            camera.zoom = camera.zoom + amount * .1f;
        }*/
    }
}



