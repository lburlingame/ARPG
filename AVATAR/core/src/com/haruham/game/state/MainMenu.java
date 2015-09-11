package com.haruham.game.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


/**
 * Created on 5/16/2015.
 */


public class MainMenu extends GameState {

    private BitmapFont font = new BitmapFont();

    private Stage stage;
    TextButton play;
    TextButtonStyle style;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("menu/uiskin.json"), new TextureAtlas("menu/uiskin.atlas"));


        style = new TextButtonStyle();
        style.font = font;



        play = new TextButton("Play",  skin);
        play.setPosition(200, 300);
        play.setColor(Color.WHITE);
        play.setSize(500, 200);
        stage.addActor(play);

    }

    public void update(float delta) {
        stage.act(delta);
    }

    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
    }

    @Override
    public void renderDebug() {

    }

    public void dispose() {

    }


}
