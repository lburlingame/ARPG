package com.haruham.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created on 5/16/2015.
 */
public class InputHandler extends InputAdapter {

    public boolean keyDown(int k) {
        if (k == Input.Keys.A) {
            Inputs.setKey(Inputs.BUTTON1, true);
        }
        if (k == Input.Keys.D) {
            Inputs.setKey(Inputs.BUTTON2, true);
        }
        if (k == Input.Keys.SPACE) {
            Inputs.setKey(Inputs.BUTTON3, true);
        }
        return true;
    }

    public boolean keyUp(int k) {
        if (k == Input.Keys.A) {
            Inputs.setKey(Inputs.BUTTON1, false);
        }
        if (k == Input.Keys.D) {
            Inputs.setKey(Inputs.BUTTON2, false);
        }
        if (k == Input.Keys.SPACE) {
            Inputs.setKey(Inputs.BUTTON3, false);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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

        return true;
    }
}
