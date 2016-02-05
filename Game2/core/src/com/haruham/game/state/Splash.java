package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.haruham.game.input.InputHandler;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.SplashInput;

/**
 * Created on 9/10/2015.
 */
public class Splash extends GameState {

    private Sprite splash;
    private ParticleEffect effect;

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

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/particles/ember.p"), Gdx.files.internal("effects/particles"));
        effect.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        effect.start();
        sin = new SplashInput(gsm);
        start = TimeUtils.millis();

    }

    public void update(float delta) {
        sin.update();
        if (TimeUtils.millis()>(start+990) && !played) {
            wavSound.play(.5f, .75f, 1);
            played = true;
        }
        effect.update(delta);

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
        effect.draw(batch);
        batch.end();
        frames++;
    }

    public void renderDebug() {

    }

    public void enter() {
        Gdx.graphics.setCursor(InputHandler.cursorEmpty);

    }

    public void exit() {
        Gdx.graphics.setCursor(InputHandler.cursorUp);
    }

    public void dispose() {
        wavSound.dispose();
        effect.dispose();
    }
}
