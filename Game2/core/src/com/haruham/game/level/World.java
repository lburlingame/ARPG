package com.haruham.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.haruham.game.gfx.Art;
import com.haruham.game.gfx.particle.ParticleEmitter;
import com.haruham.game.obj.*;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.NullInput;
import com.haruham.game.item.Item;
import com.haruham.game.obj.Character;
import com.haruham.game.state.Play;
import com.haruham.game.util.Util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created on 9/11/2015.
 */

// abstract class world, all worlds extend from it, and all collision handlng and sound effects and whatnot will be handled in the abstract super class
public class World {

    private OrthographicCamera hudCamera;
    private Play play;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private FrameBuffer fbo;
    
    private OrthographicCamera camera;
    private TileMap tmap;

    private BitmapFont font = new BitmapFont();

  //  private Sound coinpickup = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/coins1.wav"));

    private Sound ambient = Gdx.audio.newSound(Gdx.files.internal("audio/catacombs.wav"));
    private Sound cast = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/firebolt2.wav"));
    private Sound sizzle = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/sizzle2.wav"));

    private Inventory inventory = new Inventory(5);

    private Character player;

    private ArrayList<GameObject> objects;  // all game objects are also in this list for sorting purposes

    // these lists are for collision detections
    private ArrayList<Character> characters;
    private ArrayList<Character> neutral; // neutral characters, these are town npcs that cannot be attacked and will not release, but can be interacted with
    private ArrayList<AttackObject> attacks;
    private ArrayList<Character> dead;
    private ArrayList<Item> items;
    private ArrayList<Pickup> pickups;

    private ParticleEmitter emitter;
    //other drops;

    //temp control variable
    private float collisionsound = 0;
    private static final float collisionreset = .15f;
    private static final float dropoff = 150;



    /*

    LIGHT STUFF  -- LIGHTQUALITY, use lower resolution tex if it actually helps fps on laptop
     */

    //used for drawing
    private boolean	lightMove = false;
    public boolean lightOscillate = true;


    //values passed to the shader


    //used to make the light flicker
    public float zAngle;
    public static final float zSpeed = 12.0f;
    public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;



    public World(Play play) {
        this.play = play;

        camera = play.getCamera();
        hudCamera = play.getHudCamera();
        shapeRenderer = play.getShapeRenderer();
        batch = play.getBatch();
        fbo = play.getFBO();


        ambient.loop(2f);

        tmap = new TileMap("levels/test_map.txt", camera);

        objects = new ArrayList<>(); // list of all objects in world, for sorting/rendering purposes
        characters = new ArrayList<>();
        dead = new ArrayList<>();
        attacks = new ArrayList<>();
        items = new ArrayList<>();
        pickups = new ArrayList<>();

        emitter = new ParticleEmitter();

        player = play.getPlayer();
        player.setWorld(this);

        characters.add(player);
        objects.add(player);

        for (int i = 0; i < 400; i++) {
            addCharacter(new Character(this, 1, new NullInput(), new Vector3((float) (Math.random() * 150 + 200), (float) (Math.random() * 150 + 200), 0)));
        }


        camera.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        camera.position.set(player.getX(),player.getY(),0);
        camera.update();

    }

    public void update(float delta) {
        camera.unproject(Inputs.pos);

        tmap.update(delta);

        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).update(delta);
            if (!attacks.get(i).isActive()) {
                removeAttack(attacks.get(i));
                i--;
            }
        }

        for (int i = 0; i < characters.size(); i++) {
            characters.get(i).update(delta);

            float maxVol = 0;
            float maxPan = 0;
            // add sound effects to a queue, play a couple every update?
            for (int j = 0; j < attacks.size(); j++) {
                if (attacks.get(j).collidesWith(characters.get(i)) && !attacks.get(j).hasCollided(characters.get(i))) {
                    attacks.get(j).onCollision(characters.get(i));
                 //   if (collisionsound <= 0) {
                        float distance = Util.findDistance(camera.position, characters.get(i).getPosition());
                        float xdist = camera.position.x - characters.get(i).getX();

                        float pan = xdist / -100;
                        if (pan > 1) pan = 1;
                        if (pan < -1) pan = -1;

                        if (distance < dropoff) distance = dropoff;
                        float volume = dropoff / distance;
                        volume *= volume * .4f;

                        if (volume > maxVol) {
                            maxVol = volume;
                            maxPan = pan;
                        }


                    //}
                    emitter.bloodSpatter(characters.get(i).getPosition(), new Vector3(attacks.get(j).getDx()*.2f, attacks.get(j).getDy()*.2f,(float)Math.random() * 180 - 90f), 100);
                    //if (!alive) {
                        addPickup(new Coin(characters.get(i).getPosition().add(0, 0, 16), new Vector3((float) (Math.random() * 180 - 90), (float) (Math.random() * 180 - 90), (float) (Math.random() * 45 + 45)), characters.get(i).getGold()));
                        if (Math.random() < .05f) {
                            addPickup(new HealthGlobe(characters.get(i).getPosition().add(0,0,16), new Vector3((float) (Math.random() * 180 - 90), (float) (Math.random() * 180 - 90), (float) (Math.random() * 45 + 45)), 100));
                        }
                        //chars.remove(j);
                    //}

                }
            }

            if (maxVol > .01) {
                sizzle.play(maxVol, (float) (Math.random() * .15 + .85), maxPan);
                collisionsound = (float) (Math.random() * collisionreset + .15f);
            }
        }

        for (int i = 0; i < pickups.size(); i++) {
            pickups.get(i).update(delta);
            if (pickups.get(i).collidesWith(player)) {
                pickups.get(i).pickup(player);
                removePickup(pickups.get(i));
                i--;
            }
        }
        emitter.update(delta);

        lerp(player.getPosition());

        if (collisionsound > 0) {
            collisionsound -= delta;
        }
    }

    public void render() {
        camera.update();

        fbo.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setShader(play.getDefaultShader());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        int bdest = batch.getBlendDstFunc();

        batch.setBlendFunction(batch.getBlendSrcFunc(), GL20.GL_ONE);
        //float lightSize = lightOscillate? (700 + 10f * (float)Math.sin(zAngle) + .2f* MathUtils.random()):700;
        zAngle += .014 *zSpeed;
        while(zAngle > PI2)
            zAngle -= PI2;

        float lightSize = lightOscillate? (350 + 4f * (float)Math.sin(zAngle) + .2f* MathUtils.random()):350;
        batch.draw(Art.light, 1000- lightSize*0.5f,1000-lightSize*0.5f, lightSize, lightSize);

        batch.draw(Art.light, player.getX() - lightSize * 0.5f, player.getY() - lightSize * 0.5f, lightSize, lightSize);
        lightSize = lightOscillate? (600 + 7f * (float)Math.sin(zAngle) + .2f* MathUtils.random()):600;

        for (int i = 0; i < attacks.size(); i++) {
            batch.draw(Art.light,  attacks.get(i).getX()- lightSize*0.5f, attacks.get(i).getY() - lightSize*0.5f, lightSize, lightSize);
        }

        batch.setBlendFunction(batch.getBlendSrcFunc(), bdest);

        batch.end();
        fbo.end();


        batch.setProjectionMatrix(camera.combined);
        batch.setShader(play.getCurrentShader());
        batch.begin();
        fbo.getColorBufferTexture().bind(1); //this is important! bind the FBO to the 2nd texture unit
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);

        tmap.draw(batch);
        emitter.draw(batch);
        Collections.sort(objects);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(batch);
        }


        batch.setShader(play.getDefaultShader());
        batch.setProjectionMatrix(hudCamera.combined);
        font.draw(batch,"(" + play.format.format(play.lightrgb[0]) + ", " + play.format.format(play.lightrgb[1]) + ", " + play.format.format(play.lightrgb[2]) + ")," + play.format.format(play.lightrgb[3]), hudCamera.viewportWidth - 150, hudCamera.viewportHeight - 20);

        batch.end();

       /* shapeRenderer.setColor(new Color(.0f, .0f, .05f, 0.4f));
        font.draw(batch, shapeRenderer.getColor().r + ", " + shapeRenderer.getColor().g + ", " + shapeRenderer.getColor().b + ", " + shapeRenderer.getColor().a, hudCamera.viewportWidth - 150, hudCamera.viewportHeight - 40);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 0, hudCamera.viewportWidth, hudCamera.viewportHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);*/
    }

    public void renderDebug() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setAutoShapeType(true);

        shapeRenderer.begin();
        // abstract draw debug method in gameobject
        for (int i = 0; i < characters.size(); i++) {
            characters.get(i).drawDebug(shapeRenderer);
        }

        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).drawDebug(shapeRenderer);
        }

        for (int i = 0; i < pickups.size(); i++) {
            pickups.get(i).drawDebug(shapeRenderer);
        }

        shapeRenderer.end();
    }

    // lerps the game camera to position;
    // need to change so that its called based on player pos
    public void lerp(Vector3 pos) {
        float lerp = .05f;//.0125f;
        Vector3 position = camera.position;
        position.x += (pos.x - position.x) * lerp;
        position.y += (pos.y - position.y) * lerp * 1.5;
    }


    public void start() {
        ambient.resume();
    }

    public void stop() {
        ambient.pause();
    }

    public void dispose() {
        fbo.dispose();

        ambient.dispose();
        sizzle.dispose();
        font.dispose();

    }

    public void addAttack(AttackObject attack) {
        attacks.add(attack);
        objects.add(attack);
        cast.play(.1f, 1.25f, 0f);
    }


    public void removeAttack(AttackObject attack) {
        attacks.remove(attack);
        objects.remove(attack);
    }

    public void addPickup(Pickup pickup) {
        pickups.add(pickup);
        objects.add(pickup);
    }

    private void removePickup(Pickup pickup) {
        pickups.remove(pickup);
        objects.remove(pickup);
    }

    public void addCharacter(Character character) {
        characters.add(character);
        objects.add(character);
    }

    public void addNeutral(Character character) {

    }

    public void addItem(Item item) {

    }


    public ParticleEmitter getEmitter() {
        return emitter;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public ArrayList<AttackObject> getAttacks() {
        return attacks;
    }

    public void clearAttacks() {
        for (int i = 0; i < attacks.size(); i++) {
            removeAttack(attacks.get(i));
            i--;
        }
    }


    // remove this stuff later


}



/*

                    float opacity = 1.0f;
                    if (player != null) {
                        double distance = Util.findDistance(x - player.x, y - player.y);
                        opacity = (float)(distance/cycle.getLightDist());
                        if (opacity > cycle.getMaxDarkness()) {
                            opacity = cycle.getMaxDarkness();
                        }
                    }
                    if (opacity != 0) {




 */


/*
        PRODUCES GHOST LIKE EFFECTS, REALLY  COOL

       // batch.setBlendFunction(gl.BLEND_SRC_ALPHA,gl.BLEND_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        //batch.enableBlending();

        batch.setBlendFunction(batch.getBlendSrcFunc(), GL20.GL_ONE);

        //batch.setBlendFunction(bsrc, bdest);





 */
