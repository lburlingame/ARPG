package com.haruham.game.state;



import com.badlogic.gdx.Gdx;
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
import com.haruham.game.entity.Player;
import com.haruham.game.handler.PlayerContactListener;
import com.haruham.game.input.GameMenuInput;
import com.haruham.game.input.Inputs;
import com.haruham.game.input.PlayerInput;
import com.haruham.game.level.TileMap;
import javafx.stage.Screen;


/**
 * Created on 5/16/2015.
 */
public class Play extends GameState {

    private TiledMap map;
    private TileMap tmap;

    private OrthogonalTiledMapRenderer renderer;
    private ShapeRenderer debugRenderer;
    private Entity char1;

    private long start;
    private int frames = 0;

    private int fps = 0;

    public static float w;
    public static float h;

    private BitmapFont font = new BitmapFont();

    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;

    private World world;
    private Body playerBody;
    private PlayerContactListener contact;

    private GameMenuInput gin;

    public Play(GameStateManager gsm) {
        super(gsm);

        gin = new GameMenuInput(gsm);


        start = TimeUtils.millis();
        font = new BitmapFont();

        world = new World(new Vector2(0,-9.81f), true);
        b2dr = new Box2DDebugRenderer();

        tmap = new TileMap("levels/test_map.txt", gsm);
        map = new TmxMapLoader().load("levels/testmap2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        debugRenderer = new ShapeRenderer();

        char1 = new Entity(1, new PlayerInput(game), 1, new Vector3(300,300,0));
        camera.position.set(char1.getX() + char1.getWidth()/2,char1.getY() + char1.getHeight()/2,0);
    }


    public void update(float delta) {
        gin.update();
        world.step(delta, 6, 2);
       // tmap.update(delta);
        char1.update(delta);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float lerp = .05f;//.0125f;
        Vector3 position = camera.position;
        position.x += (char1.getX() + char1.getWidth()/2 - position.x) * lerp ;
        position.y += (char1.getY() + char1.getHeight()/2 - position.y) * lerp *1.5;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        //tmap.draw(batch);

        char1.draw(batch);

        batch.end();

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
        font.draw(batch, Inputs.pos.x + ", " + Inputs.pos.y, 10, Gdx.graphics.getHeight() - 40);
        camera.unproject(Inputs.pos);
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
        map.dispose();
    }


}
