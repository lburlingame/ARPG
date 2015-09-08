package com.haruham.game.state;

import com.haruham.game.GameMain;

import java.util.Stack;

/**
 * Created on 5/16/2015.
 */
public class GameStateManager {

    private GameMain game;

    private Stack<GameState> gameStates;

    public static final int PLAY = 850822;

    public GameStateManager(GameMain game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public GameMain getGame() {
        return game;
    }

    public void update(float delta) {
        gameStates.peek().update(delta);
    }

    public void render() {
        gameStates.peek().render();
    }

    private GameState getState(int state) {
        if (state == PLAY) return new Play(this);
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState state = gameStates.pop();
        state.dispose();
    }

}
