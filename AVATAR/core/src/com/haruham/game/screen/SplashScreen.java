package com.haruham.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.haruham.game.GameMain;

/**
 * Created on 5/14/2015.
 */
public class SplashScreen implements Screen {

    private GameMain game;
    private SpriteBatch batch;
    private Sprite splash;

    private long start;
    private int frames = 0;

    public SplashScreen(GameMain game) {
        this.game = game;
        batch = new SpriteBatch();
        splash = new Sprite(new Texture("sprites/bear_sprite.png"));
        splash.setSize(32*5, 32*5);
        splash.setX(Gdx.graphics.getWidth() / 2 - splash.getWidth() / 2);
        splash.setY(Gdx.graphics.getHeight() / 2 - splash.getHeight() / 2);
        start = TimeUtils.millis();
    }

    @Override
    public void show() {

       // splash.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splash.draw(batch);
        batch.end();

        frames++;

        if (TimeUtils.millis()>(start+1000)) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.app.log("splash", "hide called");
        Gdx.app.log("splash", "Rendered " + frames + " times.");
    }

    @Override
    public void dispose() {
        batch.dispose();
        splash.getTexture().dispose();
    }
}
