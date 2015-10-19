package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haruham.game.input.GameMenuInput;

/**
 * Created on 10/13/2015.
 */
public class GameMenu extends GameState {

    private GameMenuInput gmin;

    public GameMenu(GameStateManager gsm) {
        super(gsm);
        gmin = new GameMenuInput(gsm);
    }

    public void update(float delta) {
        gmin.update();
    }

    public void render() {
        shapeRenderer.setColor(new Color(0, 0, 0, 0.85f));
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

    }
}
