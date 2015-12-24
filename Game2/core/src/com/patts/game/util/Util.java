package com.patts.game.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector2;
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
