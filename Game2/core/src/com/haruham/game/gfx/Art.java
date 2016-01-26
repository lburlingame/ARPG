package com.haruham.game.gfx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created on 10/14/2015.
 */
public class Art {
    public static TextureRegion LightningSegment, HalfCircle, HalfCircle2, Pixel;
    public static TextureRegion light;
    public static TextureRegion shadow;

    public static void load(AssetManager assetManager){
        assetManager.load("effects/HalfCircle.png", Texture.class);
        assetManager.load("effects/LightningSegment.png", Texture.class);
        assetManager.load("effects/Pixel.png", Texture.class);
        assetManager.load("lighttest/light3.png", Texture.class);
        assetManager.load("effects/shadow.png", Texture.class);

    }
    public static void assignResource(AssetManager assetManager){
        LightningSegment = new TextureRegion(assetManager.get("effects/LightningSegment.png", Texture.class));
        HalfCircle =  new TextureRegion(assetManager.get("effects/HalfCircle.png", Texture.class));
        Pixel =  new TextureRegion(assetManager.get("effects/Pixel.png", Texture.class));
        HalfCircle2 =  new TextureRegion(assetManager.get("effects/HalfCircle.png", Texture.class));
        HalfCircle2.flip(true, false);

        light =  new TextureRegion(assetManager.get("lighttest/light3.png", Texture.class));
        shadow =  new TextureRegion(assetManager.get("effects/shadow.png", Texture.class));
    }

    public static void unload(AssetManager assetManager){
        assetManager.unload("effects/HalfCircle.png");
        assetManager.unload("effects/LightningSegment.png");
        assetManager.unload("effects/Pixel.png");
        assetManager.unload("lighttest/light3.png");
        assetManager.unload("effects/shadow.png");

    }
}