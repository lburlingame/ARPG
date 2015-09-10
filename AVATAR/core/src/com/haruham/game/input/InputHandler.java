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

    private GameStateManager gsm;
    private OrthographicCamera camera;


    public InputHandler(GameStateManager gsm) {
        this.gsm = gsm;
        camera = gsm.getGame().getCamera();
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
        if ((camera.viewportHeight > 250 && amount < 0) || (camera.viewportHeight < 1000 && amount > 0)) {
            camera.viewportWidth += 100 * amount;
            camera.viewportHeight += 56.25 * amount;
            //camera.zoom = 1.5f;
        }
        return true;
    }
}
