package com.patts.game.gfx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created on 10/14/2015.
 */
public class Art {
    public static TextureRegion LightningSegment, HalfCircle, HalfCircle2, Pixel;
    public static void load(AssetManager assetManager){
        assetManager.load("effects/HalfCircle.png", Texture.class);
        assetManager.load("effects/LightningSegment.png", Texture.class);
        assetManager.load("effects/Pixel.png", Texture.class);
    }
    public static void assignResource(AssetManager assetManager){
        LightningSegment = new TextureRegion(assetManager.get("effects/LightningSegment.png", Texture.class));
        HalfCircle =  new TextureRegion(assetManager.get("effects/HalfCircle.png", Texture.class));
        Pixel =  new TextureRegion(assetManager.get("effects/Pixel.png", Texture.class));
        HalfCircle2 =  new TextureRegion(assetManager.get("effects/HalfCircle.png", Texture.class));
        HalfCircle2.flip(true, false);
    }
}