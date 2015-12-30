package com.haruham.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.haruham.game.input.GameMenuInput;

/**
 * Created on 10/13/2015.
 */
public class GameMenu extends GameState {

    private BitmapFont font = new BitmapFont();

    private GameMenuInput gmin;

    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private Skin skin;

    private ImageButton resume;
    private ImageButton settings;
    private ImageButton exit;

    private Sound boop;

    public GameMenu(GameStateManager gsm) {
        super(gsm);
        gmin = new GameMenuInput(gsm);

        boop = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/boop.ogg"));

        stage = new Stage();

        table = new Table();
        table.setFillParent(true);
        table.setSize(400, 400);
        table.center();
        /// table.center();
        //table.setPosition(75, 175);
        stage.addActor(table);

        atlas = new TextureAtlas("menu/igm/igm.pack");
        skin = new Skin();
        skin.addRegions(atlas);

        resume = new ImageButton(skin.getDrawable("resume0"));
        resume.getStyle().over = skin.getDrawable("resume1");

        resume.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                resumeClicked();
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.125f);
            }
        });
        table.row().colspan(1);
        table.add(resume).width(512).height(48);

        settings = new ImageButton(skin.getDrawable("settings0"));
        settings.getStyle().over = skin.getDrawable("settings1");

        settings.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                settingsClicked();
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.125f);
            }
        });
        table.row().colspan(1);
        table.add(settings).width(512).height(48);

        exit = new ImageButton(skin.getDrawable("exit0"));
        exit.getStyle().over = skin.getDrawable("exit1");

        exit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                exitClicked();
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.125f);
            }
        });
        table.row().colspan(1);
        table.add(exit).width(512).height(48);

    }

    public void resumeClicked() {
        gsm.popState(true);
    }
    public void settingsClicked() {
        gsm.pushState(GameStateManager.SETTINGS);
    }
    public void exitClicked() {
        gsm.leavePlay();
        gsm.setState(GameStateManager.MAINMENU);

    }

    public void update(float delta) {
        gmin.update();
        stage.act(delta);

    }

    public void render() {
        shapeRenderer.setColor(new Color(0, 0, 0, 0.85f));
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(hudCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0,0,hudCamera.viewportWidth, hudCamera.viewportHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        stage.draw();
    }

    public void renderDebug() {

    }

    public void enter() {
        game.getInputs().addProcessor(stage);

    }

    public void exit() {
        game.getInputs().removeProcessor(stage);

    }

    public void dispose() {
        boop.dispose();
        skin.dispose();
        atlas.dispose();
        stage.dispose();
        font.dispose();


    }
}
