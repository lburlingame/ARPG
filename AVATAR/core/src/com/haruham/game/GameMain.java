package com.haruham.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haruham.game.input.InputHandler;
import com.haruham.game.input.Inputs;
import com.haruham.game.state.GameStateManager;

public class GameMain implements ApplicationListener {

    public static final String TITLE = "RPG";
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 3;

    public static final float STEP = 1 / 60f;
    private float delta;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    private GameStateManager gsm;

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public OrthographicCamera getHudCamera() {
        return hudCamera;
    }

    public void create() {
        Gdx.input.setInputProcessor(new InputHandler());
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
        hudCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
        gsm = new GameStateManager(this);
    }

    public void resize(int width, int height) {

    }

    public void render() {
        delta += Gdx.graphics.getDeltaTime();
        while (delta >= STEP) {
            delta -= STEP;
            gsm.update(STEP);
            gsm.render();
            Inputs.update();
        }
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {
        batch.dispose();
    }
}
