package com.haruham.game.state;



import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.haruham.game.entity.Entity;
import com.haruham.game.entity.Inventory;
import com.haruham.game.gfx.LightRenderer;
import com.haruham.game.gfx.particle.ParticleEmitter;
import com.haruham.game.input.GameMenuInput;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.NullInput;
import com.haruham.game.input.PlayerInput;
import com.haruham.game.item.Attack;
import com.haruham.game.item.Item;
import com.haruham.game.level.Level;
import com.haruham.game.level.TileMap;
import com.haruham.game.level.World;

import java.util.ArrayList;


/**
 * Created on 5/16/2015.
 */
public class Play extends GameState {

    private BitmapFont font;


    private GameMenuInput gin;

    private ArrayList<Level> levels;

    public Play(GameStateManager gsm) {
        super(gsm);
        //wavSound.loop(.4f, 1f,.1f);
        font = new BitmapFont();
        gin = new GameMenuInput(gsm);
        levels = new ArrayList<>();
        levels.add(new Level(this));
    }


    public void update(float delta) {
        gin.update();
        levels.get(0).update(delta);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        levels.get(0).render();

        batch.begin();
        batch.setProjectionMatrix(hudCamera.combined);
        int fps = Gdx.graphics.getFramesPerSecond();
        hudCamera.update();
        font.draw(batch, fps + " ", 10, Gdx.graphics.getHeight() - 20);
        font.draw(batch, Inputs.posScreen.x + ", " + Inputs.posScreen.y, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, (Inputs.pos.x) + ", " + (Inputs.pos.y), 10, Gdx.graphics.getHeight() - 60);
        font.draw(batch, (camera.zoom+ " "), 10, Gdx.graphics.getHeight() - 80);
        //font.draw(batch, attacks.size() + "", 10, Gdx.graphics.getHeight() - 100);

        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.85f));
        shapeRenderer.rect(0,0,hudCamera.viewportWidth, hudCamera.viewportHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    public void renderDebug() {
        levels.get(0).renderDebug();
    }

    public void dispose() {
        for (int i = 0; i < levels.size(); i++) {
            levels.get(i).dispose();
        }
    }

    public void start() {
        levels.get(0).start();
    }

    public void stop() {
        levels.get(0).stop();
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
