package com.haruham.game.state;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haruham.game.input.GameMenuInput;
import com.haruham.game.input.Inputs;
import com.haruham.game.level.World;

import java.util.ArrayList;


/**
 * Created on 5/16/2015.
 */
public class Play extends GameState {

    private BitmapFont font;


    private GameMenuInput gin;

    private ArrayList<World> worlds;

    // sound manager needs to be here, observer to level

    public Play(GameStateManager gsm) {
        super(gsm);
        //wavSound.loop(.4f, 1f,.1f);
        font = new BitmapFont();
        gin = new GameMenuInput(gsm);
        worlds = new ArrayList<>();
        worlds.add(new World(this));
    }


    public void update(float delta) {
        gin.update();
        worlds.get(0).update(delta);
    }

    /*
        batch.setColor(new Color(.1f,1,.1f,1f));
        will change color tints to create a certain effect
    */
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worlds.get(0).render();

        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        int fps = Gdx.graphics.getFramesPerSecond();
        font.draw(batch, fps + " ", 10, Gdx.graphics.getHeight() - 20);
        font.draw(batch, Inputs.posScreen.x + ", " + Inputs.posScreen.y, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, (Inputs.pos.x) + ", " + (Inputs.pos.y), 10, Gdx.graphics.getHeight() - 60);
        font.draw(batch, (camera.zoom+ " "), 10, Gdx.graphics.getHeight() - 80);
        //font.draw(batch, attacks.size() + "", 10, Gdx.graphics.getHeight() - 100);


        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.line(hudCamera.viewportWidth / 2, hudCamera.viewportHeight * .3f, hudCamera.viewportWidth / 2, hudCamera.viewportHeight*.7f);
        shapeRenderer.line(hudCamera.viewportWidth * .3f, hudCamera.viewportHeight / 2, hudCamera.viewportWidth * .7f, hudCamera.viewportHeight / 2);

        shapeRenderer.end();
        /*
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.85f));
        shapeRenderer.rect(0,0,hudCamera.viewportWidth, hudCamera.viewportHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);*/

    }

    public void renderDebug() {
        worlds.get(0).renderDebug();
    }

    public void dispose() {
        for (int i = 0; i < worlds.size(); i++) {
            worlds.get(i).dispose();
        }
    }

    public void start() {
        worlds.get(0).start();
    }

    public void stop() {
        worlds.get(0).stop();
    }


    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
