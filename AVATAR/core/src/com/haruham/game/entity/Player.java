package com.haruham.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created on 5/14/2015.
 */
public class Player implements InputProcessor {

    private Vector3 pos;
    private Vector3 vel;

    public Sprite sprite;

    private float speed = 420;
    private float gravity = 60;

    private OrthographicCamera camera;

    public Player(Sprite sprite, Vector3 pos, OrthographicCamera camera) {
        this.sprite = sprite;
        this.pos = pos;
        this.camera = camera;

        vel = new Vector3();
        sprite.setX(pos.x);
        sprite.setY(pos.y);
//        sprite.scale(.25f);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(float delta) {
        //vel.y -= gravity * delta;

        if (vel.y > speed) {
            vel.y = speed;
        }else if (vel.y < -speed) {
            vel.y = -speed;
        }

        pos.x += vel.x * delta;
        pos.y += vel.y * delta;

        sprite.setX(pos.x);
        sprite.setY(pos.y);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                vel.y = 180;
                break;
            case Input.Keys.S:
                vel.y = -180;
                break;
            case Input.Keys.A:
                vel.x = -180;
                break;
            case Input.Keys.D:
                vel.x = 180;
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                if (vel.y > 0) {
                    vel.y = 0;
                }

                break;
            case Input.Keys.S:
                if (vel.y < 0) {
                    vel.y = 0;
                }
                break;
            case Input.Keys.A:
                if (vel.x < 0) {
                    vel.x = 0;
                }
                break;
            case Input.Keys.D:
                if (vel.x > 0) {
                    vel.x = 0;
                }
                break;

        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if ((camera.viewportHeight > 100 && amount < 0) || (camera.viewportHeight < 1000 && amount > 0)) {
            camera.viewportWidth += 100 * amount;
            camera.viewportHeight += 56.25 * amount;
            camera.zoom = 1.5f;
        }

        return true;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getZ() {
        return pos.z;
    }

    public float getDx() {
        return vel.x;
    }

    public float getDy() {
        return vel.y;
    }



    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public Vector3 getPosition() {
        return pos;
    }
}
