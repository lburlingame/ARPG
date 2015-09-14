package com.haruham.game.state;

import com.haruham.game.GameApp;

import java.util.Stack;

/**
 * Created on 5/16/2015.
 */
public class GameStateManager {

    private GameApp game;

    private Stack<GameState> gameStates;

    private Play play;

    public static final int SPLASH = 0;
    public static final int MAINMENU = 1;
    public static final int PLAY = 2;

    public GameStateManager(GameApp game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(SPLASH);
    }

    public GameApp getGame() {
        return game;
    }

    public void update(float delta) {
        gameStates.peek().update(delta);
    }

    public void render() {
        gameStates.peek().render();
        if (game.debug) {
            gameStates.peek().renderDebug();
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

        return null;
    }

    public void setState(int state) {
        popState(true);
        pushState(state);
    }

    public void pushState(int state) {
        if (gameStates.size() > 0) {
            gameStates.peek().removeInput();
        }
        GameState gameState = getState(state);
        gameState.addInput();
        gameStates.push(gameState);
    }

    public void popState(boolean dispose) {
        GameState gameState = gameStates.pop();
        gameState.removeInput();
        if (dispose) {
            gameState.dispose();
        }
        if (gameStates.size() > 0) {
            gameStates.peek().addInput();
        }
    }

    public void leavePlay() {
        if (play != null) {
            play.dispose();
            play = null;
        }
    }

    public void dispose() {
        while (gameStates.size() > 0) {
            gameStates.pop().dispose();
        }
    }

    public boolean isPlaying() {
        return play != null;
    }
}
