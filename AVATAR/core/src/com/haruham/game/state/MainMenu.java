package com.haruham.game.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MainMenu extends GameState {

    private BitmapFont font = new BitmapFont();

    private Stage stage;
    private TextButton play;
    private ImageButton play_b;
    private Table table;

    private ShapeRenderer shapeRenderer;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        stage = new Stage();


        table = new Table();
        table.setFillParent(true);
        table.setSize(400, 400);
        table.center();
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("menu/scene2d/uiskin.json"), new TextureAtlas("menu/scene2d/uiskin.atlas"));

      /*  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white");
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);*/

        play = new TextButton("Play",  skin);
      /*  play.setPosition(200, 300);
        //play.setSize(400, 100);
        play.getLabel().setFontScale(1.5f);*/
        play.addListener(new ClickListener()
        {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                playClicked();
            }
        });
        table.add(play).width(400).height(66);

        //play_b = new ImageButton(new Texture("menu/play.png"));
        //stage.addActor(play);
        game.getInput().addProcessor(stage);
        Gdx.input.setInputProcessor(game.getInput());
    }

    public void playClicked() {
        gsm.setState(GameStateManager.PLAY);
    }

    public void update(float delta) {
        stage.act(delta);

    }

    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
       // table.drawDebug(shapeRenderer); // This is optional, but enables debug lines for tables.


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
