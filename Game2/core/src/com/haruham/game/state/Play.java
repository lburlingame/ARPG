package com.haruham.game.state;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.haruham.game.gfx.Art;
import com.haruham.game.input.PlayInput;
import com.haruham.game.input.Inputs;
import com.haruham.game.level.World;

import java.util.ArrayList;


/**
 * Created on 5/16/2015.
 */
public class Play extends GameState {

    private BitmapFont font;


    private PlayInput pin;

    private ArrayList<World> worlds;

    // sound manager needs to be here, observer to level

    public Play(GameStateManager gsm) {
        super(gsm);
        //wavSound.loop(.4f, 1f,.1f);
        font = new BitmapFont();
        pin = new PlayInput(gsm);

        // Create assets manager
        AssetManager assetManager = new AssetManager();
        // create a new sprite batch to render the graphics
        Art.load(assetManager);
        assetManager.finishLoading();
        Art.assignResource(assetManager);

        worlds = new ArrayList<>();
        worlds.add(new World(this));
    }


    public void update(float delta) {
        pin.update();
        worlds.get(0).update(delta);
    }

    /*
        batch.setColor(new Color(.1f,1,.1f,1f));
        will change color tints to create a certain effect
    */
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*Rectangle scissors = new Rectangle(); use this to clip the screen for rendering, maybe to give focus on a certain element
        Rectangle clipBounds = new Rectangle(camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight);
        ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.pushScissors(scissors);
        batch.flush();
        ScissorStack.popScissors();*/

        worlds.get(0).render();

    }

    public void renderDebug() {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        int fps = Gdx.graphics.getFramesPerSecond();
        font.draw(batch, fps + " ", 10, Gdx.graphics.getHeight() - 20);
        font.draw(batch, Inputs.posScreen.x + ", " + Inputs.posScreen.y, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, (Inputs.pos.x) + ", " + (Inputs.pos.y), 10, Gdx.graphics.getHeight() - 60);
        font.draw(batch, (camera.zoom+ " "), 10, Gdx.graphics.getHeight() - 80);
        //font.draw(batch, attacks.size() + "", 10, Gdx.graphics.getHeight() - 100);


        batch.end();

        shapeRenderer.setColor(new Color(1,1,1,1));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.line(hudCamera.viewportWidth / 2, hudCamera.viewportHeight * .3f, hudCamera.viewportWidth / 2, hudCamera.viewportHeight*.7f);
        shapeRenderer.line(hudCamera.viewportWidth * .3f, hudCamera.viewportHeight / 2, hudCamera.viewportWidth * .7f, hudCamera.viewportHeight / 2);

        shapeRenderer.end();
        worlds.get(0).renderDebug();
    }

    public void dispose() {
        for (int i = 0; i < worlds.size(); i++) {
            worlds.get(i).dispose();
        }
    }

    public void enter() {
        worlds.get(0).start();
    }

    public void exit() {
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
