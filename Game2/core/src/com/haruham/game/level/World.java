package com.haruham.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.obj.Entity;
import com.haruham.game.obj.Inventory;
import com.haruham.game.gfx.particle.ParticleEmitter;
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
public class World {

    private Play playState;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private TileMap tmap;

    private BitmapFont font = new BitmapFont();

    private Sound wavSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/pickup1.wav"));

    private Sound ambient = Gdx.audio.newSound(Gdx.files.internal("audio/catacombs.wav"));
    private Sound cast = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/firebolt2.wav"));
    private Sound sizzle = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/sizzle2.wav"));
  //  private LightRenderer lights;

    private Inventory inventory = new Inventory(5);

    private Entity player;

    private ArrayList<Entity> entities;
    private ArrayList<Attack> attacks;
    private ArrayList<Entity> dead;
    private ArrayList<Item> items;
    private ArrayList<Item> coins;

    private ParticleEmitter emitter;
    //other drops;

    public World(Play playState) {
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
        player = new Entity(this, 1, new PlayerInput(), new Vector3(300,300,0));

        entities.add(player);
        entities.add(new Entity(this, 1, new NullInput(), new Vector3(600,600,0)));
      //  lights = new LightRenderer();
        //lights.addLight(player);


        camera.position.set(player.getX() + player.getWidth()/2,player.getY() + player.getHeight()/2,0);

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
                    sizzle.play(1f);  /// .08
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
        position.x += (player.getX() - position.x) * lerp;
        position.y += (player.getY() + player.getHeight()/2 - position.y) * lerp * 1.5;
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
