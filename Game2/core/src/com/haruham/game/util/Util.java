package com.haruham.game.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector3;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 4/18/2015.
 */
public class Util {


    public static float findDistance(float x, float y) {
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public static float findDistance(Vector3 o, Vector3 d) {
        return (float)Math.sqrt(Math.pow(d.x - o.x, 2) + Math.pow(d.y - o.y, 2));
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


    /*
            y = mx
            h2 = x2 + y2

            h2 = x2 + (mx)2
            h2 = x2 + m2x2
            h2 = x2(1 + m2)
            h2/(1+m2) = x2
            sqrt(h2/(1+m2)) = x
     */

    public static float findX(float hypotenuse, float slope) {
        return (float) Math.sqrt(Math.pow(hypotenuse, 2) / (1 + Math.pow(slope, 2)));
    }


    public static float getAngle(Vector3 target0, Vector3 target1) {
        float angle = (float)Math.toDegrees(Math.atan2(target1.y, target1.x) - Math.atan2(target0.y, target0.x)); // get the angle between the 2 vectors

        // normalize
        if(angle > 180){
            angle = angle - 360;
        }
        if (angle < -180) {
            angle = angle + 360;
        }
       // System.out.println(target0.x + ", " + target0.y + "       :          " + target1.x + ", " + target1.y + "    ========  " + angle);
        return angle;
    }

    public static void saveScreenshot() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmssSSS");
        //get current date time with Date()
        Date date = new Date();
        String name = dateFormat.format(date);
        FileHandle file = Gdx.files.local("screenshots/" + name + ".png");
        Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        try {
            PixmapIO.writePNG(file,pixmap);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


    public static void saveScreenshot(FileHandle file) {

        Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        try {
            PixmapIO.writePNG(file,pixmap);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {
        Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);

        final Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        ByteBuffer pixels = pixmap.getPixels();
        Gdx.gl.glReadPixels(x, y, w, h, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);

        final int numBytes = w * h * 4;
        byte[] lines = new byte[numBytes];
        if (flipY) {
            final int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
        } else {
            pixels.clear();
            pixels.get(lines);
        }

        return pixmap;
    }

  //  public static Vector2 (Vector2 center, float angle) {

   // }




    // polynomial "cosine and sine" funct to speed up calcs
}
