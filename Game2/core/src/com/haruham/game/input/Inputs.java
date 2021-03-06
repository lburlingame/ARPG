package com.haruham.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created on 5/16/2015.
 */
public class Inputs {

    public static final int NUM_KEYS = 24;

    public static boolean[] keys = new boolean[NUM_KEYS];
    public static boolean[] pkeys = new boolean[NUM_KEYS];

    public static final int W = 0;
    public static final int S = 1;
    public static final int A = 2;
    public static final int D = 3;

    public static final int SPACE = 4;

    public static final int ESCAPE = 5;
    public static final int I = 6;
    public static final int P = 16;

    public static final int N = 10;
    public static final int M = 11;

    public static final int TAB = 7;
    public static final int V = 8;
    public static final int C = 9;

    public static final int M1 = 21;
    public static final int M2 = 22;
    public static final int M3 = 23;


    public static final int ONE = 12;
    public static final int TWO = 13;
    public static final int THREE = 14;
    public static final int FOUR = 15;


    public static final int UP = 17;
    public static final int DOWN = 18;
    public static final int LEFT = 19;
    public static final int RIGHT = 20;
    public static int amount = 0;


    public static int MOVE_UP = W;
    public static int MOVE_DOWN = S;
    public static int MOVE_LEFT = A;
    public static int MOVE_RIGHT = D;
    public static int JUMP = SPACE;

    public static Vector3 pos = new Vector3(0,0,0);
    public static Vector3 posScreen = new Vector3(0,0,0);

    public static void update(OrthographicCamera camera) {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }

        pos.x = posScreen.x = Gdx.input.getX();
        pos.y = posScreen.y = Gdx.input.getY();
        camera.unproject(pos);

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

        if (i == Input.Keys.N) {
            keys[N] = b;
        }

        if (i == Input.Keys.M) {
            keys[M] = b;
        }

        if (i == Input.Keys.NUM_1) {
            keys[ONE] = b;
        }
        if (i == Input.Keys.NUM_2) {
            keys[TWO] = b;
        }

        if (i == Input.Keys.NUM_3) {
            keys[THREE] = b;
        }
        if (i == Input.Keys.NUM_4) {
            keys[FOUR] = b;
        }
        if (i == Input.Keys.P) {
            keys[P] = b;
        }


        if (i == Input.Keys.UP) {
            keys[UP] = b;
        }
        if (i == Input.Keys.DOWN) {
            keys[DOWN] = b;
        }

        if (i == Input.Keys.LEFT) {
            keys[LEFT] = b;
        }
        if (i == Input.Keys.RIGHT) {
            keys[RIGHT] = b;
        }

        if (i == Input.Keys.TAB) {
            keys[TAB] = b;
        }

        if (i == Input.Keys.C) {
            keys[C] = b;
        }
        if (i == Input.Keys.V) {
            keys[V] = b;
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
