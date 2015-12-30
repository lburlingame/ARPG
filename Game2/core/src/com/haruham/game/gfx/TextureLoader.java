package com.haruham.game.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class TextureLoader {

    private static Texture bear_tex = new Texture("sprites/bear_sheet.png");
    private static ArrayList<TextureRegion> bears;

    private static Texture fireball_tex = new Texture("effects/fireball.png");
    private static ArrayList<TextureRegion> fireballs;

    private static Texture grass_tex = new Texture("tiles/grass.png");
    private static ArrayList<TextureRegion> grass;

    public static TextureRegion getSprite(int id, int frame) {
        switch (id) {
            case (1):
                return getBear(frame);
            case (2):
                return getFireball(frame);
            default:
                return getBear(frame);
        }
    }

    private static TextureRegion getBear(int frame) {
        if (bears == null) {
            bears = new ArrayList<TextureRegion>();
            for (int i = 0; i < bear_tex.getWidth(); i+= 32) {
                bears.add(new TextureRegion(bear_tex, i, 0, 32, 32));
            }
        }
        return bears.get(frame % bears.size());
    }


    private static TextureRegion getFireball(int frame) {
        if (fireballs == null) {
            fireballs = new ArrayList<TextureRegion>();
            for (int i = 0; i < fireball_tex.getWidth(); i+= 32) {
                fireballs.add(new TextureRegion(fireball_tex, i, 0, 32, 32));
            }
        }
        return fireballs.get(frame % fireballs.size());
    }

    public static TextureRegion getTile(int id, int frame) {
        switch (id) {
            case (1):
                return getGrass(frame);
            default:
                return getGrass(frame);
        }
    }

    private static TextureRegion getGrass(int frame) {
        if (grass == null) {
            grass = new ArrayList<TextureRegion>();
            for (int i = 0; i < grass_tex.getWidth(); i+= 32) {
                grass.add(new TextureRegion(grass_tex, i, 0, 32, 32));
            }
        }
        return grass.get(frame % grass.size());
    }



}
