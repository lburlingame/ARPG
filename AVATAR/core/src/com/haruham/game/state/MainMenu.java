package com.haruham.game.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.haruham.game.audio.SoundManager;
import com.haruham.game.input.MainMenuInput;


public class MainMenu extends GameState {

    private BitmapFont font = new BitmapFont();

    private Stage stage;
    private Table table;
    private TextButton play;
    private TextButton exit;
    private ImageButton play_b;

    private ShapeRenderer shapeRenderer;

    private MainMenuInput min;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        smg.play();

        min = new MainMenuInput(gsm);

        stage = new Stage();

        table = new Table();
        table.setFillParent(true);
        table.setSize(400, 400);
        table.center();
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("menu/scene2d/uiskin.json"), new TextureAtlas("menu/scene2d/uiskin.atlas"));

        play = new TextButton("Play",  skin);

        play.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                playClicked();
            }
        });
        table.add(play).width(400).height(66);

        exit = new TextButton("Exit",  skin);
        exit.addListener(new ClickListener() {
          /*  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }*/
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.row().colspan(1);
        table.add(exit).width(400).height(66);


        //play_b = new ImageButton(new Texture("menu/play.png"));
        //stage.addActor(play);
        Gdx.input.setInputProcessor(game.getInputs());
    }

    public void playClicked() {
        gsm.pushState(GameStateManager.PLAY);
    }

    public void update(float delta) {
        min.update();
        stage.act(delta);
    }

    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        table.drawDebug(shapeRenderer); // This is optional, but enables debug lines for tables.


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
    }

    public void removeInput() {
        game.getInputs().removeProcessor(stage);
        smg.pause();
    }

    public void addInput() {
        game.getInputs().addProcessor(stage);
        smg.resume();
    }

    @Override
    public void renderDebug() {

    }

    public void dispose() {

    }
      /*  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white");
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);*/

          /*  play.setPosition(200, 300);
        //play.setSize(400, 100);
        play.getLabel().setFontScale(1.5f);*/

}
