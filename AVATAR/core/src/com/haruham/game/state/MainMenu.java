package com.haruham.game.state;

import static com.haruham.game.handler.B2DVars.PPM;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.haruham.game.GameMain;
import com.haruham.game.handler.B2DVars;
import com.haruham.game.handler.PlayerContactListener;
import com.haruham.game.input.Inputs;


/**
 * Created on 5/16/2015.
 */
public class MainMenu extends GameState {

    private BitmapFont font = new BitmapFont();

    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;

    private World world;
    private Body playerBody;
    private PlayerContactListener contact;

    public MainMenu(GameStateManager gsm) {
        super(gsm);

    }

    public void handleInput() {


    }

    public void update(float delta) {
        handleInput();
        world.step(delta, 6, 2);
    }

    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);




     /*   batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "PLAY STATE", 100, 100);
        batch.end();*/
    }

    @Override
    public void renderDebug() {

    }

    public void dispose() {

    }


}
