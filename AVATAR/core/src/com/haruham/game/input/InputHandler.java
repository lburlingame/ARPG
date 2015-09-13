package com.haruham.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haruham.game.state.GameStateManager;
import com.haruham.game.state.Play;

/**
 * Created on 5/16/2015.
 */
public class InputHandler extends InputAdapter {


    public InputHandler() {
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
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Inputs.setKey(button, false);
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
