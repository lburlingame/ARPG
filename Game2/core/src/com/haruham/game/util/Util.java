package com.haruham.game.util;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created on 4/18/2015.
 */
public class Util {


    public static float findDistance(float x, float y) {
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public static float findSquareDistance(float x, float y) {
        return x * x + y * y;
    }

    public static Direction findSlope(float x, float y, float dest_x, float dest_y)
    {
        float rise = dest_y - y;
        float run = dest_x - x;

        byte xdir = 0;
        float slope = 0;
        if (run == 0 && rise > 0) {
            slope = 200000;
            xdir = 0;
        }else if (run == 0 && rise < 0) {
            slope = -200000;
            xdir = 0;
        }else if (run != 0) {
            slope = rise / run;
            if (run > 0) {
                xdir = 1;
            }else{
                xdir = -1;
            }
        }

        return new Direction(xdir, slope);
    }

    public static float findX(float hypotenuse, float slope) {
        return (float) Math.sqrt(Math.pow(hypotenuse, 2) / (1 + Math.pow(slope, 2)));
    }


    public static float getAngle(Vector2 center, Vector2 target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - center.y, target.x - center.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public static float getAngle(Vector3 center, Vector3 target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - center.y, target.x - center.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

  //  public static Vector2 (Vector2 center, float angle) {

   // }




    // polynomial "cosine and sine" funct to speed up calcs
}
