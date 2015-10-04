package com.haruham.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import com.haruham.game.state.Play;

import java.util.ArrayList;

/**
 * Created on 9/11/2015.
 */
public class Level {

    private Play playState;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private TileMap tmap;

    private Entity char1;
    private Entity char2;

    private BitmapFont font = new BitmapFont();

    private Sound wavSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/pickup1.wav"));
    private Sound ambient = Gdx.audio.newSound(Gdx.files.internal("audio/catacombs.wav"));
    private Sound cast = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/firebolt2.wav"));
    private Sound sizzle = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/sizzle2.wav"));

    private LightRenderer lights;

    private Inventory inventory = new Inventory(5);

    private ArrayList<Entity> entities;
    private ArrayList<Attack> attacks;
    private ArrayList<Entity> dead;
    private ArrayList<Item> items;
    private ArrayList<Item> coins;

    private ParticleEmitter emitter;
    //other drops;

    public Level(Play playState) {
        this.playState = playState;

        camera = playState.getCamera();
        shapeRenderer = playState.getShapeRenderer();
        batch = playState.getBatch();



        ambient.loop(1f);




        tmap = new TileMap("levels/test_map.txt", camera);

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


        camera.position.set(char1.getX() + char1.getWidth()/2,char1.getY() + char1.getHeight()/2,0);

    }

    public void update(float delta) {
        camera.unproject(Inputs.pos);
        tmap.update(delta);

        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).update(delta);
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(delta);

            for (int j = 0; j < attacks.size(); j++) {
                if (entities.get(i).collidesWith(attacks.get(j)) && !attacks.get(j).hasHit(entities.get(i))) {
                    attacks.get(j).hit(entities.get(i));
                    sizzle.play(.25f);  /// .08
                    emitter.bloodSpatter(entities.get(i).getPosition().add(entities.get(i).getHit().getCenter()), new Vector3(attacks.get(j).getDx()*.14f, attacks.get(j).getDy()*.1f,(float)Math.random() * 3 - 1.5f));
                }
            }
        }

        emitter.update(delta);
    }

    public void render() {
        lerp(new Vector2(0,0));

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
    }

    public void renderDebug() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setAutoShapeType(true);

        shapeRenderer.begin();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).drawDebug(shapeRenderer);
        }

        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).drawDebug(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public void lerp(Vector2 pos) {
        float lerp = .05f;//.0125f;
        Vector3 position = camera.position;
        position.x += (char1.getX() - position.x) * lerp;
        position.y += (char1.getY() + char1.getHeight()/2 - position.y) * lerp * 1.5;
        camera.update();
    }

    public void start() {
        ambient.resume();
    }

    public void stop() {
        ambient.pause();
    }

    public void dispose() {

    }


    public void addAttack(Attack attack) {
        attacks.add(attack);
        cast.play(.2f, 1.25f, 0f);
    }


}
