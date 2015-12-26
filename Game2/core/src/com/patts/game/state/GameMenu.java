package com.patts.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.patts.game.audio.MusicManager;
import com.patts.game.input.GameMenuInput;
import com.patts.game.input.MainMenuInput;

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

    private TextButton resume;
    private TextButton settings;
    private TextButton exit;

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

        TextButton.TextButtonStyle resumeStyle = new TextButton.TextButtonStyle();
        resumeStyle.up = skin.getDrawable("resume0");
        resumeStyle.over = skin.getDrawable("resume1");
        resumeStyle.font = font;

        resume = new TextButton("",  resumeStyle);
        resume.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                resumeClicked();
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.5f);
            }
        });
        table.row().colspan(1);
        table.add(resume).width(512).height(48);

        TextButton.TextButtonStyle settingsStyle = new TextButton.TextButtonStyle();
        settingsStyle.up = skin.getDrawable("settings0");
        settingsStyle.over = skin.getDrawable("settings1");
        settingsStyle.font = font;

        settings = new TextButton("",  settingsStyle);
        settings.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                settingsClicked();
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.5f);
            }
        });
        table.row().colspan(1);
        table.add(settings).width(512).height(48);

        TextButton.TextButtonStyle exitStyle = new TextButton.TextButtonStyle();
        exitStyle.up = skin.getDrawable("exit0");
        exitStyle.over = skin.getDrawable("exit1");
        exitStyle.font = font;

        exit = new TextButton("",  exitStyle);
        exit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                exitClicked();
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.5f);
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
