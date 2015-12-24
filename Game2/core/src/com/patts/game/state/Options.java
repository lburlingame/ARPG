package com.patts.game.state;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.patts.game.input.OptionsInput;

/**
 * Created on 10/13/2015.
 */
public class Options extends GameState {

    private OptionsInput oin;
    private BitmapFont font;


    public Options(GameStateManager gsm) {
        super(gsm);
        oin = new OptionsInput(gsm);
        font = new BitmapFont();
    }

    public void update(float delta) {
        oin.update();
    }

    public void render() {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        font.draw(batch, "Hello World", 300, 300);
        batch.end();
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
