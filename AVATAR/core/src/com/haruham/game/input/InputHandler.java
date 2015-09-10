package com.haruham.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haruham.game.state.Play;

/**
 * Created on 5/16/2015.
 */
public class InputHandler extends InputAdapter {


    public boolean keyDown(int k) {
        if (k == Input.Keys.W) {
            Inputs.setKey(Inputs.W, true);
        }
        if (k == Input.Keys.S) {
            Inputs.setKey(Inputs.S, true);
        }
        if (k == Input.Keys.A) {
            Inputs.setKey(Inputs.A, true);
        }
        if (k == Input.Keys.D) {
            Inputs.setKey(Inputs.D, true);
        }

        if (k == Input.Keys.SPACE) {
            Inputs.setKey(Inputs.SPACE, true);
        }
        return true;
    }

    public boolean keyUp(int k) {
        if (k == Input.Keys.W) {
            Inputs.setKey(Inputs.W, false);
        }
        if (k == Input.Keys.S) {
            Inputs.setKey(Inputs.S, false);
        }
        if (k == Input.Keys.A) {
            Inputs.setKey(Inputs.A, false);
        }
        if (k == Input.Keys.D) {
            Inputs.setKey(Inputs.D, false);
        }

        if (k == Input.Keys.SPACE) {
            Inputs.setKey(Inputs.SPACE, false);
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

        OrthographicCamera camera = Play.camera;
        if ((camera.viewportHeight > 100 && amount < 0) || (camera.viewportHeight < 1000 && amount > 0)) {
            camera.viewportWidth += 100 * amount;
            camera.viewportHeight += 56.25 * amount;
            //camera.zoom = 1.5f;
        }
        return true;
    }
}
