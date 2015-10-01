package com.haruham.game.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
    private Skin skin;
    private TextButton play;
    private TextButton exit;
    private ImageButton play_b;

    private ShapeRenderer shapeRenderer;
    private Texture background;
    private Texture title;

    private MainMenuInput min;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("other/tempback1.jpg"));
        title = new Texture(Gdx.files.internal("other/TITLE.png"));

        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();*/

        smg.play();

        min = new MainMenuInput(gsm);

        stage = new Stage();

        table = new Table();
        table.setFillParent(true);
        table.setSize(400, 400);
        table.center();
        table.setPosition(table.getX(), table.getY());
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("menu/scene2d/uiskin.json"), new TextureAtlas("menu/scene2d/uiskin.atlas"));

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
                game.dispose();
            }
        });
        table.row().colspan(1);
        table.add(exit).width(400).height(66);


        //play_b = new ImageButton(new Texture("menu/play.png"));
        //stage.addActor(play);
        //skin.dispose();
        //Gdx.input.setInputProcessor(game.getInputs());
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

        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        batch.draw(background, 0, 0, hudCamera.viewportWidth, hudCamera.viewportHeight);
        batch.draw(title, hudCamera.viewportWidth/2 - title.getWidth() * hudCamera.viewportWidth / 1920 / 2, hudCamera.viewportHeight - title.getHeight() * hudCamera.viewportHeight / 1080 * 2f, title.getWidth() * hudCamera.viewportWidth / 1920, title.getHeight() * hudCamera.viewportHeight / 1080);
        batch.end();

        stage.draw();
        table.drawDebug(shapeRenderer); // This is optional, but enables debug lines for tables.
    }

    //change to start()/end()
    public void stop() {
        game.getInputs().removeProcessor(stage);
        smg.pause();
    }

    public void start() {
        game.getInputs().addProcessor(stage);
        smg.resume();
    }

    @Override
    public void renderDebug() {

    }

    public void dispose() {
        stage.dispose();
        background.dispose();
        skin.dispose();

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
