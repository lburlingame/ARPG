package com.patts.game.state;

import com.patts.game.GameApp;

import java.util.Stack;

/**
 * Created on 5/16/2015.
 */
public class GameStateManager {

    private GameApp game;

    private Stack<GameState> states;

    private Play play;

    public static final int SPLASH = 0;
    public static final int MAINMENU = 1;
    public static final int PLAY = 2;
    public static final int GAMEMENU = 3;
    public static final int OPTIONS = 4;

    public GameStateManager(GameApp game) {
        this.game = game;
        states = new Stack<GameState>();
        pushState(SPLASH);
    }

    public GameApp getGame() {
        return game;
    }

    public void update(float delta) {
        states.peek().update(delta);
    }

    public void render() {
        /*states.peek().render();
        if (game.debug) {
            states.peek().renderDebug();
        }*/

        for (int i = 0; i < states.size(); i++) {
            states.get(i).render();
        }
        if (game.debug) {
            states.peek().renderDebug();
        }
    }

    private GameState getState(int state) {
        if (state == SPLASH) return new Splash(this);
        if (state == MAINMENU) return new MainMenu(this);
        if (state == PLAY) {
            if (play == null) {
                return play = new Play(this);
            }
            return play;
        }
        if (state == GAMEMENU) return new GameMenu(this);
        if (state == OPTIONS) return new Options(this);

        return null;
    }

    public void setState(int state) {
        popState(true);
        pushState(state);
    }

    public void pushState(int state) {
        if (states.size() > 0) {
            states.peek().exit();
        }
        GameState gameState = getState(state);
        gameState.enter();
        states.push(gameState);
    }

    public void popState(boolean dispose) {
        GameState gameState = states.pop();
        gameState.exit();
        if (dispose) {
            gameState.dispose();
        }
        if (states.size() > 0) {
            states.peek().enter();
        }
    }

    public void leavePlay() {
        if (play != null) {
            play.dispose();
            play = null;
        }
    }

    public void dispose() {
        while (states.size() > 0) {
            states.pop().dispose();
        }
    }

    public boolean isPlaying() {
        return play != null;
    }
}
