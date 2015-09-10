package com.haruham.game.input;

/**
 * Created on 5/16/2015.
 */
public class Inputs {

    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 5;
    public static final int W = 0;
    public static final int S = 1;
    public static final int A = 2;
    public static final int D = 3;

    public static final int SPACE = 4;



    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int i , boolean b) {
        keys[i] = b;
    }

    public static boolean isDown(int i) { return keys[i]; }
    public static boolean isPressed(int i) {
        return keys[i] && !pkeys[i];
    }
}
