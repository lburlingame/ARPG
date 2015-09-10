package com.haruham.game.input;

import com.badlogic.gdx.Input;

/**
 * Created on 5/16/2015.
 */
public class Inputs {

    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 6;
    public static final int W = 0;
    public static final int S = 1;
    public static final int A = 2;
    public static final int D = 3;

    public static final int SPACE = 4;
    public static final int ESCAPE = 5;



    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
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
    }

    public static boolean isDown(int i) { return keys[i]; }
    public static boolean isPressed(int i) {
        return keys[i] && !pkeys[i];
    }
}
