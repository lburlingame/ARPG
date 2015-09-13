package com.haruham.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haruham.game.GameApp;
import com.haruham.game.audio.SoundManager;

/**
 * Created on 5/16/2015.
 */
public abstract class GameState {

    protected final GameStateManager gsm;
    protected final GameApp game;

    protected final SpriteBatch batch;
    protected OrthographicCamera camera;
    protected OrthographicCamera hudCamera;

    protected SoundManager smg;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        batch = game.getBatch();
        camera = game.getCamera();
        hudCamera = game.getHudCamera();
        smg = game.getSound();
    }

    public abstract void update(float delta);
    public abstract void render();
    public abstract void renderDebug();

    public abstract void addInput();
    public abstract void removeInput();

    public abstract void dispose();

}
