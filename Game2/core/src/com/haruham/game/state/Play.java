package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import com.esotericsoftware.kryonet.Listener;
import com.haruham.game.input.PlayInput;
import com.haruham.game.level.World;
import com.haruham.game.net.client.ClientProgram;
import com.haruham.game.net.server.ServerProgram;
import com.haruham.game.obj.Character;
import com.haruham.game.gfx.Art;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.PlayerInput;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created on 5/16/2015.
 */
public class Play extends GameState {

    private BitmapFont font;
    public boolean paused = false;

    private PlayInput pin;
    private ArrayList<World> worlds;

    // sound manager needs to be here, observer to level
    // create player character in charactercreation/selection state, and pass to play state, then pass that to world objects that are newly created
    // hud information can be gathered from that chracter object
    private Character player;

    public float[] lightrgb = {.5f, .5f, .7f, .65f};
    public int lightix = 0;

    private Texture light = new Texture("lighttest/light3.png");

    private AssetManager assetManager;
            ;
    private FrameBuffer fbo;

    //out different shaders. currentShader is just a pointer to the 4 others
    private ShaderSelection	shaderSelection = ShaderSelection.Final;
    private ShaderProgram currentShader;
    private ShaderProgram defaultShader;
    private ShaderProgram ambientShader;
    private ShaderProgram lightShader;
    private ShaderProgram finalShader;

    //read our shader files
    final String vertexShader = Gdx.files.local("lighttest/vertexShader.glsl").readString();
    final String defaultPixelShader = Gdx.files.local("lighttest/defaultPixelShader.glsl").readString();
    final String ambientPixelShader = Gdx.files.local("lighttest/ambientPixelShader.glsl").readString();
    final String lightPixelShader =  Gdx.files.local("lighttest/lightPixelShader.glsl").readString();
    final String finalPixelShader =  Gdx.files.local("lighttest/pixelShader.glsl").readString();
    public static DecimalFormat format = new DecimalFormat("0.##");

    ServerProgram server;
    ClientProgram client;

    public Play(GameStateManager gsm) {
        super(gsm);
        init();
    }

    public Play(GameStateManager gsm, ServerProgram server) {
        super(gsm);
        this.server = server;
        init();
    }

    public Play(GameStateManager gsm, ClientProgram client) {
        super(gsm);
        this.client = client;
        init();
    }

    public void init() {

        player = new Character(null, 1, new PlayerInput(), new Vector3(100,100,0));
        camera.setToOrtho(false,740, 416.25f);

        //wavSound.loop(.4f, 1f,.1f);
        font = new BitmapFont();
        pin = new PlayInput(gsm, this);

        // Create assets manager
        assetManager = new AssetManager();
        // create a new sprite batch to render the graphics
        Art.load(assetManager);
        assetManager.finishLoading();
        Art.assignResource(assetManager);

        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, (int)camera.viewportWidth*2, (int)camera.viewportHeight*2, false);

        ShaderProgram.pedantic = false;
        defaultShader = new ShaderProgram(vertexShader, defaultPixelShader);
        ambientShader = new ShaderProgram(vertexShader, ambientPixelShader);
        lightShader = new ShaderProgram(vertexShader, lightPixelShader);
        finalShader = new ShaderProgram(vertexShader, finalPixelShader);
        setShader(shaderSelection);

        ambientShader.begin();
        ambientShader.setUniformf("ambientColor", lightrgb[0], lightrgb[1], lightrgb[2], lightrgb[3]);
        ambientShader.end();

        lightShader.begin();
        lightShader.setUniformi("u_lightmap", 1);
        lightShader.end();

        finalShader.begin();
        finalShader.setUniformi("u_lightmap", 1);
        finalShader.setUniformf("ambientColor", lightrgb[0], lightrgb[1], lightrgb[2], lightrgb[3]);
        finalShader.end();

        lightShader.begin();
        lightShader.setUniformf("resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        lightShader.end();

        finalShader.begin();
        finalShader.setUniformf("resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        finalShader.end();

        worlds = new ArrayList<World>();
        worlds.add(new World(this));
    }


    public void update(float delta) {
        pin.update();
        if (!paused) {
            worlds.get(0).update(delta);
        }
    }

    /*
        batch.setColor(new Color(.1f,1,.1f,1f));
        will change color tints to create a certain effect
    */
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

/*
        // use for cinematic like effects
        Rectangle scissors = new Rectangle(); //use this to clip the screen for rendering, maybe to give focus on a certain element
        Rectangle clipBounds = new Rectangle(camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/4, camera.viewportWidth, camera.viewportHeight/2); // issue on camera zooming and new scissors being created with altered camera viewports, in final release of game where zooming is not allowed it should be fine
        ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.pushScissors(scissors);

        //render in here somewhere...
        batch.flush();
        ScissorStack.popScissors();
*/

        worlds.get(0).render();


    }

    public void renderDebug() {
        worlds.get(0).renderDebug();
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        font.draw(batch, Inputs.posScreen.x + ", " + Inputs.posScreen.y, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, player.getX() + ", " + player.getY(), 10, Gdx.graphics.getHeight()-60);
        font.draw(batch, camera.position.x + ", " + camera.position.y, 10, Gdx.graphics.getHeight()-80);

        font.draw(batch, worlds.get(0).getSize() + " ", 10, Gdx.graphics.getHeight() - 100);
        font.draw(batch, player.getGold() +  " " , 10, Gdx.graphics.getHeight() - 120);
        font.draw(batch, worlds.get(0).getEmitter().drawn + "" , 10, Gdx.graphics.getHeight() - 140);
        font.draw(batch, camera.viewportWidth + ", " + camera.viewportHeight , 10, Gdx.graphics.getHeight() - 160);

        //font.draw(batch, attacks.size() + "", 10, Gdx.graphics.getHeight() - 100);


        batch.end();

    /*    shapeRenderer.setColor(new Color(1,1,1,1));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.line(hudCamera.viewportWidth / 2, hudCamera.viewportHeight * .3f, hudCamera.viewportWidth / 2, hudCamera.viewportHeight*.7f);
        shapeRenderer.line(hudCamera.viewportWidth * .3f, hudCamera.viewportHeight / 2, hudCamera.viewportWidth * .7f, hudCamera.viewportHeight / 2);

        shapeRenderer.end();*/
    }

    public void dispose() {
        for (int i = 0; i < worlds.size(); i++) {
            worlds.get(i).dispose();
        }

        ambientShader.dispose();
        // defaultShader.dispose();
        finalShader.dispose();
        lightShader.dispose();
        light.dispose();
        fbo.dispose();
        Art.unload(assetManager);
        assetManager.dispose();
        if (server != null) {
            server.close();
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

    public Character getPlayer() {
        return player;
    }

    public OrthographicCamera getHudCamera() {
        return hudCamera;
    }

    public World getWorld() {
        return worlds.get(0);
    }

    public FrameBuffer getFBO() {
        return fbo;
    }

    public ShaderProgram getDefaultShader() {
        return defaultShader;
    }

    public ShaderProgram getCurrentShader() {
        return currentShader;
    }


    public enum ShaderSelection{
        Default,
        Ambient,
        Light,
        Final
    };

    public void setShader(ShaderSelection ss){
        shaderSelection = ss;

        if(ss == ShaderSelection.Final){
            currentShader = finalShader;
        }
        else if(ss == ShaderSelection.Ambient){
            currentShader = ambientShader;
        }
        else if(ss == ShaderSelection.Light){
            currentShader = lightShader;
        }
        else{
            ss = ShaderSelection.Default;
            currentShader = defaultShader;
        }
    }

    public void updateShader() {
        finalShader.begin();
        finalShader.setUniformf("ambientColor", lightrgb[0], lightrgb[1], lightrgb[2], lightrgb[3]);
        finalShader.end();
        ambientShader.begin();
        ambientShader.setUniformf("ambientColor", lightrgb[0], lightrgb[1], lightrgb[2], lightrgb[3]);
        ambientShader.end();
    }

}
