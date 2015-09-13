package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.haruham.game.GameApp;
import com.haruham.game.input.SplashInput;

/**
 * Created on 9/10/2015.
 */
public class Splash extends GameState {

    private Sprite splash;

    private long start;
    private int frames = 0;

    private SplashInput sin;


    public Splash(GameStateManager gsm) {
        super(gsm);
        splash = new Sprite(new Texture("other/fbtest.png"));
        splash.setSize(32*5, 32*5);
        splash.setX(Gdx.graphics.getWidth() / 2 - splash.getWidth() / 2);
        splash.setY(Gdx.graphics.getHeight() / 2 - splash.getHeight() / 2);
        start = TimeUtils.millis();

        sin = new SplashInput(gsm);
    }

    public void update(float delta) {
        sin.update();
    }

    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splash.draw(batch);
        batch.end();

        frames++;

        if (TimeUtils.millis()>(start+3000)) {
            gsm.setState(GameStateManager.MAINMENU);
        }
    }

    public void renderDebug() {

    }

    public void addInput() {

    }

    public void removeInput() {
    }

    public void dispose() {
    }
}
