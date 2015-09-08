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
public class Play extends GameState {

    private BitmapFont font = new BitmapFont();

    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;

    private World world;
    private Body playerBody;
    private PlayerContactListener contact;

    public Play(GameStateManager gsm) {
        super(gsm);
        b2dr = new Box2DDebugRenderer();
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, GameMain.V_WIDTH / PPM, GameMain.V_HEIGHT / PPM);
        b2dCam.viewportWidth = GameMain.V_WIDTH /  PPM;
        b2dCam.viewportHeight = GameMain.V_HEIGHT / PPM;

        b2dCam.update(); // Don't forget me ;)
        world  = new World(new Vector2(0, -8.81f), true);
        contact = new PlayerContactListener();
        world.setContactListener(contact);

        BodyDef bdef = new BodyDef();
        bdef.position.set(45 / PPM, 35 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.angle = 0f;
        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(450 / PPM, 5 / PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_GROUND;
        fdef.filter.maskBits = B2DVars.BIT_OBSTACLE | B2DVars.BIT_PLAYER;

        body.createFixture(fdef).setUserData("ground");

        bdef.position.set(160 / PPM, 80 / PPM);
        bdef.angle = 0f;
        body = world.createBody(bdef);

        shape.setAsBox(55 / PPM, 5 / PPM);
        fdef.shape = shape;

        body.createFixture(fdef).setUserData("ground");

        bdef.position.set(65 / PPM, 35 / PPM);
        bdef.angle = 1.55f;
        body = world.createBody(bdef);

        shape.setAsBox(150 / PPM, 5 / PPM);
        fdef.shape = shape;
        fdef.friction = 1f;

        body.createFixture(fdef).setUserData("wall");

        bdef.position.set(0 / PPM, 35 / PPM);
        bdef.angle = 1.55f;
        body = world.createBody(bdef);

        shape.setAsBox(3000 / PPM, 5 / PPM);
        fdef.shape = shape;

        body.createFixture(fdef).setUserData("wall");

        bdef.position.set(125 / PPM, 180 / PPM);
        bdef.angle = 1.55f;
        body = world.createBody(bdef);

        shape.setAsBox(4000 / PPM, 5 / PPM);
        fdef.shape = shape;

        body.createFixture(fdef).setUserData("wall");




        bdef.angle = 0f;

        bdef.position.set(120 / PPM, 200 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        playerBody = world.createBody(bdef);
        playerBody.setFixedRotation(true);
        shape.setAsBox(5 / PPM,5 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_WALL;
        fdef.friction = .7f;
        fdef.density = .8f;
        fdef.restitution = 0f;
        playerBody.createFixture(fdef).setUserData("player:body");

        shape.setAsBox(4 / PPM, 3/ PPM, new Vector2(0 / PPM,-7 / PPM),0);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_GROUND;
        fdef.isSensor = false;
        playerBody.createFixture(fdef).setUserData("player:foot");

        shape.setAsBox(2 / PPM, 6/ PPM, new Vector2(-5 / PPM,0 / PPM),0);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_GROUND;
        fdef.isSensor = true;
        playerBody.createFixture(fdef).setUserData("player:left");

        shape.setAsBox(2 / PPM, 6/ PPM, new Vector2(5 / PPM,0 / PPM),0);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_GROUND;
        fdef.isSensor = true;
        playerBody.createFixture(fdef).setUserData("player:right");

    }

    public void handleInput() {
        if (Inputs.isDown(Inputs.BUTTON1)) {
            if (contact.playerOnGround) {
                //playerBody.applyForceToCenter(-.05f,0,true);
                playerBody.setLinearVelocity(-85 / PPM, playerBody.getLinearVelocity().y);
            }else{
                //playerBody.applyForceToCenter(-.05f,0,true);
                playerBody.setLinearVelocity(-60 / PPM, playerBody.getLinearVelocity().y);
            }
        }else if(Inputs.isDown(Inputs.BUTTON2)) {
            if (contact.playerOnGround) {
               // playerBody.applyForceToCenter(.05f,0,true);
                playerBody.setLinearVelocity(85 / PPM, playerBody.getLinearVelocity().y);
            }else{
               // playerBody.applyForceToCenter(.05f,0,true);
                playerBody.setLinearVelocity(60 / PPM, playerBody.getLinearVelocity().y);
            }
        }
        if (Inputs.isPressed(Inputs.BUTTON3)) {
            if (contact.playerOnGround) {
                playerBody.applyForceToCenter(0,3.5f,true);
            }else if (contact.playerOnWallLeft) {
                playerBody.setLinearVelocity(100 / PPM, 0);
                playerBody.applyForceToCenter(0,3.5f,true);
            }else if (contact.playerOnWallRight) {
                playerBody.setLinearVelocity(-100 / PPM, 0);
                playerBody.applyForceToCenter(0,3.5f,true);
            }
        }

    }

    public void update(float delta) {
        handleInput();
        world.step(delta, 6, 2);
    }

    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dCam.position.set(playerBody.getPosition().x, playerBody.getPosition().y, 0); // x and y could be changed by Keyboard input for example

        b2dCam.update(); // Don't forget me ;)
        b2dr.render(world, b2dCam.combined);


     /*   batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "PLAY STATE", 100, 100);
        batch.end();*/
    }

    public void dispose() {

    }


}
