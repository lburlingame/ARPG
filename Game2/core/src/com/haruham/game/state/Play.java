package com.haruham.game.state;



import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.haruham.game.GameApp;
import com.haruham.game.entity.Entity;
import com.haruham.game.entity.Inventory;
import com.haruham.game.gfx.LightRenderer;
import com.haruham.game.handler.PlayerContactListener;
import com.haruham.game.input.GameMenuInput;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.NullInput;
import com.haruham.game.input.PlayerInput;
import com.haruham.game.item.Attack;
import com.haruham.game.item.Item;
import com.haruham.game.level.TileMap;
import javafx.stage.Screen;

import java.util.ArrayList;


/**
 * Created on 5/16/2015.
 */
public class Play extends GameState {

    private TiledMap map;
    private TileMap tmap;

    private OrthogonalTiledMapRenderer renderer;
    private ShapeRenderer debugRenderer;
    private Entity char1;
    private Entity char2;

    private long start;

    private int fps = 0;

    private BitmapFont font = new BitmapFont();

    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;

    private World world;
    private RayHandler rayHandler;

    private Body playerBody;
    private PlayerContactListener contact;
    private Sound wavSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/pickup1.wav"));
    private LightRenderer lights;

    private GameMenuInput gin;

    private Inventory inventory = new Inventory(5);

    private ArrayList<Entity> entities;
    private ArrayList<Attack> attacks;
    private ArrayList<Item> items;
    //other drops;

    public Play(GameStateManager gsm) {
        super(gsm);
        //wavSound.loop(.4f, 1f,.1f);

        gin = new GameMenuInput(gsm);


        start = TimeUtils.millis();
        font = new BitmapFont();



        tmap = new TileMap("levels/test_map.txt", camera);
        map = new TmxMapLoader().load("levels/testmap2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        debugRenderer = new ShapeRenderer();

        lights = new LightRenderer();

        entities = new ArrayList<>();
        attacks = new ArrayList<>();
        items = new ArrayList<>();

        char1 = new Entity(this, 1, new PlayerInput(), 1, new Vector3(300,300,0));
        char2 = new Entity(this, 1, new NullInput(), 1, new Vector3(600,600,0));
        lights.addLight(char1);

        world = new World(new Vector2(0,0),false);
        rayHandler = new RayHandler(world);
        new PointLight(rayHandler,1000, Color.BLUE,2000,char1.getX(),char1.getY());

        camera.position.set(char1.getX() + char1.getWidth()/2,char1.getY() + char1.getHeight()/2,0);
    }


    public void update(float delta) {
        camera.unproject(Inputs.pos);
        gin.update();
        //world.step(delta, 6, 2);
        tmap.update(delta);
        char1.update(delta);
        char2.update(delta);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float lerp = .05f;//.0125f;
        Vector3 position = camera.position;
        position.x += (char1.getX() - position.x) * lerp ;
        position.y += (char1.getY() + char1.getHeight()/2 - position.y) * lerp * 1.5;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        //renderer.setView(camera);
        //renderer.render();
        //rayHandler.setCombinedMatrix(camera);


        batch.begin();

        tmap.draw(batch);

        batch.end();

        lights.render(camera);
        batch.begin();
        char1.draw(batch);
        char2.draw(batch);

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(batch);
        }


        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).draw(batch);
        }

        batch.end();
        //rayHandler.render();


    }

    public void renderDebug() {
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.setAutoShapeType(true);

        debugRenderer.begin();
        char1.drawDebug(debugRenderer);
        debugRenderer.end();

        batch.begin();
        batch.setProjectionMatrix(hudCamera.combined);
        fps = Gdx.graphics.getFramesPerSecond();
        hudCamera.update();
        font.draw(batch, fps + " ", 10, Gdx.graphics.getHeight() - 20);
        font.draw(batch, Inputs.posScreen.x + ", " + Inputs.posScreen.y, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, (Inputs.pos.x) + ", " + (Inputs.pos.y), 10, Gdx.graphics.getHeight() - 60);
        font.draw(batch, (camera.zoom+ " "), 10, Gdx.graphics.getHeight() - 80);

        batch.end();
    }

    public void addInput() {

    }

    public void removeInput() {

    }

    public void dispose() {
        renderer.dispose();
        debugRenderer.dispose();
        wavSound.dispose();
        world.dispose();
        font.dispose();
        map.dispose();
        lights.dispose();
    }


    public void addAttack(Attack attack) {
        attacks.add(attack);
    }
}
