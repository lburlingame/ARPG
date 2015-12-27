package com.patts.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patts.game.input.InputHandler;
import com.patts.game.input.Inputs;
import com.patts.game.state.GameStateManager;
import org.lwjgl.openal.AL;

import java.io.BufferedReader;

public class GameApp implements ApplicationListener {

    public static final String TITLE = "RPG";
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 3;

    public static final float STEP = 1/60f;
    private float delta;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;

    private GameStateManager gsm;

    private InputMultiplexer inputs = new InputMultiplexer();
    private InputHandler inputHandler;
    public boolean debug = true;
    public boolean mute = false;

    public void create() {
        try {
            FileHandle file = Gdx.files.local("log/runcount.txt");
            BufferedReader br = file.reader(1);
            String curLine = br.readLine();
            br.close();
            int current = Integer.parseInt(curLine);
            current++;
            System.out.println(current + " ");
            file.writeString(current + "", false);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/2, h/2);

        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, w, h);

        gsm = new GameStateManager(this);

        inputHandler = new InputHandler();
        inputs.addProcessor(inputHandler);
        Gdx.input.setInputProcessor(inputs);



    }

    public void resize(int width, int height) {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        hudCamera.setToOrtho(false, w, h);
    }

    public void render() {
        /*delta += Gdx.graphics.getDeltaTime();
        while (delta >= STEP) {
            delta -= STEP;
            gsm.update(STEP);
            gsm.render();
            Inputs.update();
        }*/

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
        Inputs.update();
    }

    public void dispose() {
        gsm.dispose();
        batch.dispose();
       // System.out.println("dispose");
        AL.destroy();
        System.exit(1);
    }

    public InputMultiplexer getInputs() {
        return inputs;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public OrthographicCamera getHudCamera() {
        return hudCamera;
    }

    public void pause() {
        System.out.println("pause");
    }

    public void resume() {
        System.out.println("Resume");
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}