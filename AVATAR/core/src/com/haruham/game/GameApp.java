package com.haruham.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haruham.game.input.GameInput;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.WindowInput;
import com.haruham.game.state.GameStateManager;

public class GameApp implements ApplicationListener {

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
    private WindowInput win;
    public boolean debug = true;


    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/2, h/2);

        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, w, h);
        gsm = new GameStateManager(this);
        Gdx.input.setInputProcessor(new GameInput());
        win = new WindowInput(gsm);
    }

    public void resize(int width, int height) {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        hudCamera.setToOrtho(false, w, h);
    }

    public void render() {
        delta += Gdx.graphics.getDeltaTime();
        while (delta >= STEP) {
            delta -= STEP;
            gsm.update(STEP);
            gsm.render();
            win.update();
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


    public SpriteBatch getBatch() {
        return batch;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public OrthographicCamera getHudCamera() {
        return hudCamera;
    }

}
