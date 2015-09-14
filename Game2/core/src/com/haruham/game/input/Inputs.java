package com.haruham.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created on 5/16/2015.
 */
public class Inputs {

    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 10;

    public static final int W = 0;
    public static final int S = 1;
    public static final int A = 2;
    public static final int D = 3;

    public static final int SPACE = 4;

    public static final int ESCAPE = 5;
    public static final int I = 6;

    public static final int M1 = 7;
    public static final int M2 = 8;
    public static final int M3 = 9;

    public static int amount = 0;


    public static int MOVE_UP = W;
    public static int MOVE_DOWN = S;
    public static int MOVE_LEFT = A;
    public static int MOVE_RIGHT = D;
    public static int JUMP = SPACE;

    public static Vector3 pos = new Vector3(0,0,0);

    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }

        pos.x = Gdx.input.getX();
        pos.y = Gdx.input.getY();

        amount = 0;
    }

    public static void setKey(int i, boolean b) {
        if (i == Input.Keys.W) {
            keys[W] = b;
        }
        if (i == Input.Keys.S) {
            keys[S] = b;
        }
        if (i == Input.Keys.A) {
            keys[A] = b;
        }
        if (i == Input.Keys.D) {
            keys[D] = b;
        }

        if (i == Input.Keys.SPACE) {
            keys[SPACE] = b;
        }

        if (i == Input.Keys.ESCAPE) {
            keys[ESCAPE] = b;
        }

        if (i == Input.Keys.I) {
            keys[I] = b;
        }

        if (i == Input.Buttons.LEFT) {
            keys[M1] = b;
        }

        if (i == Input.Buttons.RIGHT) {
            keys[M3] = b;
        }
    }

    public static boolean isDown(int i) { return keys[i]; }
    public static boolean isPressed(int i) {
        return keys[i] && !pkeys[i];
    }
    public static boolean isReleased(int i) {
        return !keys[i] && pkeys[i];
    }

}
