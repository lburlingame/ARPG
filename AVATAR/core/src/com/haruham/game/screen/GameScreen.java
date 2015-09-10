package com.haruham.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.haruham.game.GameMain;
import com.haruham.game.entity.Entity;
import com.haruham.game.entity.Player;
import com.haruham.game.input.PlayerInput;

/**
 * Created on 5/14/2015.
 */
public class GameScreen implements Screen {

    private GameMain game;

    private TiledMap map;

    private World world;
    private Box2DDebugRenderer b2dr;

    // private OrthogonalTiledMapRenderer renderer;
    private OrthogonalTiledMapRenderer renderer;

    public static OrthographicCamera camera;
    private OrthographicCamera UIcamera;

    private SpriteBatch batch;

    private Player character;
    private Entity char1;
    private Texture img;

    private long start;
    private int frames = 0;

    private int fps = 0;
    private BitmapFont font;

    public static float w;
    public static float h;

    public GameScreen(GameMain game) {
        this.game = game;

        /*Pixmap pm = new Pixmap(Gdx.files.internal("gui/d2_cursor.png"));
        Gdx.input.setCursorImage(pm, 0, 0);
        pm.dispose();*/

        start = TimeUtils.millis();
        font = new BitmapFont();

        world = new World(new Vector2(0,-9.81f), true);
        b2dr = new Box2DDebugRenderer();

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        UIcamera = new OrthographicCamera();
        UIcamera.setToOrtho(false, w, h);

        map = new TmxMapLoader().load("levels/testmap2.tmx");
     //   renderer = new OrthogonalTiledMapRenderer(map);
        renderer = new OrthogonalTiledMapRenderer(map);

        batch = new SpriteBatch();

        img = new Texture("sprites/bear_sprite.png");
        character = new Player(new Sprite(img), new Vector3(0, 0, 0), camera);
        char1 = new Entity(1, new PlayerInput(game), 1, new Vector3(32,32,32));

        /*img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);*/ /*//** for scaling **//*/
        sprite = new Sprite(texture);
        sprite.setSize(0.1f, 0.3f); /*//** sprite size in screen coordinates **//*/
        sprite.setOrigin(0.05f,0); //*/

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float lerp = .05f;//.0125f;
        Vector3 position = camera.position;
        position.x += (char1.getX() + char1.getWidth()/2 - position.x) * lerp ;
        position.y += (char1.getY() + char1.getHeight()/2 - position.y) * lerp *1.5;
        //camera.position.set(character.getX() + character.getWidth()/2, character.getY() + character.getHeight() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        character.update(delta);
        char1.update(delta);
        renderer.setView(camera);
        renderer.render();

        fps = Gdx.graphics.getFramesPerSecond();

        batch.begin();
        character.draw(batch);
        char1.draw(batch);
        //batch.draw(img, character.getX(), character.getY());
        //batch.setProjectionMatrix(camera.invProjectionView);
        batch.setProjectionMatrix(UIcamera.combined);
        fps = Gdx.graphics.getFramesPerSecond();

        font.draw(batch, fps + " ", 10, Gdx.graphics.getHeight() - 20);
        batch.end();

        frames++;
    }

    public void renderDebug()

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 4;
        camera.viewportHeight = height / 4;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.app.log("game", "hide called");
        Gdx.app.log("game", "Rendered " + frames + " times.");
    }

    @Override
    public void dispose() {
        batch.dispose();
        character.dispose();
    }


}
