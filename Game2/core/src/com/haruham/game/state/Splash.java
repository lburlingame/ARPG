package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    private Sound wavSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/pickup1.wav"));
    private boolean played = false;


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
        if (TimeUtils.millis()>(start+990) && !played) {
            wavSound.play(.5f, .75f, 1);
            played = true;
        }

        if (TimeUtils.millis()>(start+4000)) {
            gsm.setState(GameStateManager.MAINMENU);
        }
    }

    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (TimeUtils.millis()>(start+1000)) {
            splash.draw(batch);
        }
        batch.end();

        frames++;
    }

    public void renderDebug() {

    }

    public void start() {

    }

    public void stop() {
    }

    public void dispose() {
        wavSound.dispose();
    }
}
