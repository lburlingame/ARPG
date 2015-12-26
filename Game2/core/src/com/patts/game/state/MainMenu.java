package com.patts.game.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    private TextureAtlas atlas;
    private Skin skin;
    private TextButton play;
    private TextButton settings;
    private TextButton quit;

    private ShapeRenderer shapeRenderer;

    private Texture background;
    private Texture title;

    private MainMenuInput min;
    private MusicManager mmg;
    private Music rainloop;
    private Sound boop;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("other/tempback1.jpg"));
        title = new Texture(Gdx.files.internal("other/TITLE.png"));

        rainloop = Gdx.audio.newMusic(Gdx.files.internal("audio/rainloop.ogg"));
        rainloop.setLooping(true);
        rainloop.setVolume(1f);
        rainloop.play();

        boop = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/boop.ogg"));
        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();*/

        mmg = new MusicManager();//use observer for sound, but check to see if it happened on screenahh
        mmg.play();
        mmg.pause();


        min = new MainMenuInput(gsm);

        stage = new Stage();

        table = new Table();
       // table.setFillParent(true);
        table.setSize(400, 400);
        table.left().center();
       /// table.center();
        table.setPosition(75, 175);
        stage.addActor(table);

        atlas = new TextureAtlas("menu/main/mm.pack");
        skin = new Skin();
        skin.addRegions(atlas);



        TextButtonStyle playStyle = new TextButtonStyle();
        playStyle.up = skin.getDrawable("play0");
        playStyle.over = skin.getDrawable("play1");
        playStyle.font = font;

        play = new TextButton("",  playStyle);
        play.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                playClicked();
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.5f);
            }
        });
        table.row().colspan(1);
        table.add(play).width(192).height(64);


        TextButtonStyle settingsStyle = new TextButtonStyle();
        settingsStyle.up = skin.getDrawable("settings0");
        settingsStyle.over = skin.getDrawable("settings1");
        settingsStyle.font = font;

        settings = new TextButton("",  settingsStyle);
        settings.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                playClicked();
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.5f);
            }
        });
        table.row().colspan(1);
        table.add(settings).width(256).height(64).padLeft(64);


        TextButtonStyle quitStyle = new TextButtonStyle();
        quitStyle.up = skin.getDrawable("quit0");
        quitStyle.over = skin.getDrawable("quit1");
        quitStyle.font = font;

        quit = new TextButton("",  quitStyle);
        quit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.dispose();
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                boop.play(.5f);
            }
        });
        table.row().colspan(1);
        table.add(quit).width(192).height(64);




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

    public void enter() {
        game.getInputs().addProcessor(stage);
        rainloop.play();
    }

    //change to enter()/end()
    public void exit() {
        game.getInputs().removeProcessor(stage);
        rainloop.pause();
    }

    @Override
    public void renderDebug() {

    }

    public void dispose() {
        stage.dispose();
        background.dispose();
        title.dispose();
        skin.dispose();
        atlas.dispose();
        mmg.dispose();
        boop.dispose();
        rainloop.dispose();
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
