package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haruham.game.input.GameMenuInput;
import com.haruham.game.input.Inputs;

import static com.haruham.game.state.GameStateManager.GAMEMENU;
import static com.haruham.game.state.GameStateManager.PLAY;

/**
 * Created on 2/23/2016.
 */
public class Dead extends GameState {

    private BitmapFont font = new BitmapFont();

    private GameMenuInput gmin;

    private Sound boop;

    public Dead(GameStateManager gsm) {
        super(gsm);
        gmin = new GameMenuInput(gsm);

        boop = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/boop.ogg"));


    }

    public void update(float delta) {
       // gmin.update();
        if (Inputs.isPressed(Inputs.ESCAPE)) {
            gsm.pushState(GAMEMENU);
        }

        if (Inputs.isPressed(Inputs.M1)) { // change to F9 or something
            gsm.popState(true);
        }

    }

    public void render() {
        shapeRenderer.setColor(new Color(0, 0, 0, 0.7f));
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0,0,hudCamera.viewportWidth, hudCamera.viewportHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    public void renderDebug() {

    }

    public void enter() {

    }

    public void exit() {

    }

    public void dispose() {
        boop.dispose();
        font.dispose();
    }
}
