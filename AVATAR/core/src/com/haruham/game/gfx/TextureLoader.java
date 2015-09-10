package com.haruham.game.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class TextureLoader {

    private static Texture bear_tex = new Texture("sprites/bear_sheet.png");
    private static ArrayList<TextureRegion> bears;


    public static TextureRegion getSprite(int id, int frame) {
        switch (id) {
            case (1):
                return getBear(frame);
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



}
