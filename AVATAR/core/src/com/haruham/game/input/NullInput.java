package com.haruham.game.input;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.haruham.game.GameMain;

/**
 * Created on 5/2/2015.
 */
public class NullInput extends InputComponent{

    public NullInput(GameMain game) {
        super(game);
    }

    @Override
    public void update() {

    }

    public Vector2 getScreenLoc() {
        return new Vector2(0,0);
    }
}
