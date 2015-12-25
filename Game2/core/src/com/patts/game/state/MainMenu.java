package com.patts.game.state;


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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.patts.game.audio.MusicManager;
import com.patts.game.input.MainMenuInput;


public class MainMenu extends GameState {

    private BitmapFont font = new BitmapFont();

    private Stage stage;
    private Table table;
    private Skin skin;
    private TextButton play;
    private TextButton exit;

    private TextureAtlas playAtlas;
    private Skin playSkin;
    private ImageButton imgtest;
    private ImageButton play_b;

    private ShapeRenderer shapeRenderer;
    private Texture background;
    private Texture title;

    private Texture button0;
    private Texture button1;

    private MainMenuInput min;
    private MusicManager mmg;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("other/tempback1.jpg"));
        title = new Texture(Gdx.files.internal("other/TITLE.png"));

        button0 = new Texture(Gdx.files.internal("menu/main/play0s.png"));
        button1 = new Texture(Gdx.files.internal("menu/main/play1s.png"));

        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();*/

        mmg = new MusicManager();//use observer for sound, but check to see if it happened on screenahh
        mmg.play();

        min = new MainMenuInput(gsm);

        stage = new Stage();

        table = new Table();
        table.setFillParent(true);
        table.setSize(400, 400);
        table.center();
        table.setPosition(table.getX(), table.getY());
        stage.addActor(table);

/*
        skin = new Skin(Gdx.files.internal("menu/scene2d/uiskin.json"), new TextureAtlas("menu/scene2d/uiskin.atlas"));



        exit = new TextButton("Exit",  skin);
        exit.addListener(new ClickListener() {
            */
/*  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                  return true;
              }
              public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                  Gdx.app.exit();
              }*//*

            public void clicked(InputEvent event, float x, float y) {
                game.dispose();
            }
        });
*/

        // imgtest = new ImageButton(skin,
        table.row().colspan(1);
        table.add(exit).width(400).height(66);


        playAtlas = new TextureAtlas("menu/main/mm_play.pack");
        playSkin = new Skin();
        playSkin.addRegions(playAtlas);

        TextButtonStyle style = new TextButtonStyle();
        style.up = playSkin.getDrawable("play0s");
        style.over = playSkin.getDrawable("play1s");
        style.font = font;

        play = new TextButton("",  style);
        play.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                playClicked();
            }
        });
        table.add(play).width(button0.getWidth()).height(button0.getHeight());



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
        Gdx.gl20.glClearColor(.15f,.15f,.15f,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
       // batch.draw(background, 0, 0, hudCamera.viewportWidth, hudCamera.viewportHeight);
        //batch.draw(title, hudCamera.viewportWidth/2 - title.getWidth() * hudCamera.viewportWidth / 1920 / 2, hudCamera.viewportHeight - title.getHeight() * hudCamera.viewportHeight / 1080 * 2f, title.getWidth() * hudCamera.viewportWidth / 1920, title.getHeight() * hudCamera.viewportHeight / 1080);
        //batch.draw(button0,hudCamera.viewportWidth/2 - button1.getWidth() * hudCamera.viewportWidth / 1920 / 2, hudCamera.viewportHeight - button1.getHeight() * hudCamera.viewportHeight / 1080 * 2f, button1.getWidth(), button1.getHeight());
        batch.end();

        stage.draw();
        table.drawDebug(shapeRenderer); // This is optional, but enables debug lines for tables.
    }

    //change to enter()/end()
    public void exit() {
        game.getInputs().removeProcessor(stage);
        mmg.pause();
    }

    public void enter() {
        game.getInputs().addProcessor(stage);
        mmg.play();
    }

    @Override
    public void renderDebug() {

    }

    public void dispose() {
        stage.dispose();
        background.dispose();
        title.dispose();
        button0.dispose();
        button1.dispose();
        skin.dispose();
        mmg.dispose();
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
