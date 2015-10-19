package com.haruham.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haruham.game.GameApp;
import com.haruham.game.audio.SoundManager;

/**
 * Created on 5/16/2015.
 */
public abstract class GameState {

    protected final GameStateManager gsm;
    protected final GameApp game;

    protected final SpriteBatch batch;
    protected final ShapeRenderer shapeRenderer;
    protected OrthographicCamera camera;
    protected OrthographicCamera hudCamera;


    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        shapeRenderer = game.getShapeRenderer();
        batch = game.getBatch();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
    }

    public abstract void update(float delta);
    public abstract void render();
    public abstract void renderDebug();

    // enter exit
    public abstract void enter();
    public abstract void exit();

    public abstract void dispose();

}
