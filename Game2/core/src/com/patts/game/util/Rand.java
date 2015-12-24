package com.patts.game.util;

/**
 * Created on 12/15/2015.
 */
public class Rand {

    private static float[] nums = {0, .09f, .05f, .15f, .93f, .6f, .79f, .19f, .63f, .99f, .53f, .44f, .66f, .23f, .95f, .59f, .23f, .52f, .79f, .91f, .85f, .43f, .48f, .36f, .26f, .49f, .82f, .94f};
    private static int ix = 0;
    private static int size = nums.length;


    public static float getNext() {
        return nums[ix++%size];
    }

}
