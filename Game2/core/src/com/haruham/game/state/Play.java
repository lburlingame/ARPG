package com.haruham.game.state;



import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.haruham.game.level.TileMap;
import com.haruham.game.level.World;

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

    private World world;

    private Sound wavSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/pickup1.wav"));
    private Sound ambient = Gdx.audio.newSound(Gdx.files.internal("audio/catacombs.wav"));
    private Sound cast = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/firebolt2.wav"));
    private Sound sizzle = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/sizzle2.wav"));


    private LightRenderer lights;

    private GameMenuInput gin;

    private Inventory inventory = new Inventory(5);

    private ArrayList<Entity> entities;
    private ArrayList<Attack> attacks;
    private ArrayList<Entity> dead;
    private ArrayList<Item> items;
    private ArrayList<Item> coins;


    private ParticleEmitter emitter;
    //other drops;

    public Play(GameStateManager gsm) {
        super(gsm);
        //wavSound.loop(.4f, 1f,.1f);

        gin = new GameMenuInput(gsm);
        ambient.loop(1f);

        start = TimeUtils.millis();
        font = new BitmapFont();



        tmap = new TileMap("levels/test_map.txt", camera);
        map = new TmxMapLoader().load("levels/testmap2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        debugRenderer = new ShapeRenderer();

        entities = new ArrayList<>();
        dead = new ArrayList<>();
        attacks = new ArrayList<>();
        items = new ArrayList<>();

        emitter = new ParticleEmitter();
        char1 = new Entity(this, 1, new PlayerInput(), 1, new Vector3(300,300,0));
        char2 = new Entity(this, 1, new NullInput(), 1, new Vector3(600,600,0));

        entities.add(char1);
        entities.add(char2);
        lights = new LightRenderer();
        lights.addLight(char1);

       // world = new World(new Vector2(0,0),false);
        //rayHandler = new RayHandler(world);

        camera.position.set(char1.getX() + char1.getWidth()/2,char1.getY() + char1.getHeight()/2,0);
    }


    public void update(float delta) {
        camera.unproject(Inputs.pos);
        gin.update();
        //world.step(delta, 6, 2);
        tmap.update(delta);

        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).update(delta);
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(delta);

            for (int j = 0; j < attacks.size(); j++) {
                if (entities.get(i).collidesWith(attacks.get(j)) && !attacks.get(j).hasHit(entities.get(i))) {
                    attacks.get(j).hit(entities.get(i));
                    sizzle.play(.25f);
                    emitter.bloodSpatter(entities.get(i).getPosition(), new Vector3((float)Math.random() * 3 - 1.5f,(float)Math.random() * 3 - 1.5f,(float)Math.random() * 3));
                }
            }
        }

        emitter.update(delta);
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


        batch.begin();

        tmap.draw(batch);

        /*batch.end();
        lights.render(camera);
        batch.begin();*/
        emitter.draw(batch);

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
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).drawDebug(debugRenderer);
        }

        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).drawDebug(debugRenderer);
        }
        debugRenderer.end();

        batch.begin();
        batch.setProjectionMatrix(hudCamera.combined);
        fps = Gdx.graphics.getFramesPerSecond();
        hudCamera.update();
        font.draw(batch, fps + " ", 10, Gdx.graphics.getHeight() - 20);
        font.draw(batch, Inputs.posScreen.x + ", " + Inputs.posScreen.y, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, (Inputs.pos.x) + ", " + (Inputs.pos.y), 10, Gdx.graphics.getHeight() - 60);
        font.draw(batch, (camera.zoom+ " "), 10, Gdx.graphics.getHeight() - 80);
        font.draw(batch, attacks.size() + "", 10, Gdx.graphics.getHeight() - 100);

        batch.end();
    }

    public void start() {
        ambient.resume();
    }

    public void stop() {
        ambient.pause();
    }

    public void dispose() {
        renderer.dispose();
        debugRenderer.dispose();
        wavSound.dispose();
        font.dispose();
        map.dispose();
        lights.dispose();
    }


    public void addAttack(Attack attack) {
        attacks.add(attack);
        cast.play(.2f);
    }
}
