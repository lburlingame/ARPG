package com.haruham.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created on 5/16/2015.
 */
public class InputHandler extends InputAdapter {

    Cursor cursorUp = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("gui/cursor_up.png")), 0, 4);
    Cursor cursorDown = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("gui/cursor_down.png")), 0, 4);


    public InputHandler() {
        Gdx.graphics.setCursor(cursorUp);

    }


    public boolean keyDown(int k) {
        Inputs.setKey(k, true);
        return true;
    }

    public boolean keyUp(int k) {
        Inputs.setKey(k, false);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Inputs.setKey(button, true);
        Gdx.graphics.setCursor(cursorDown);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Inputs.setKey(button, false);
        Gdx.graphics.setCursor(cursorUp);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        Inputs.amount = amount;
        return true;
    }
}
