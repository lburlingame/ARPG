package com.haruham.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haruham.game.obj.GameObject;
import com.haruham.game.util.Event;
import com.haruham.game.util.Observer;
import com.haruham.game.util.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 9/13/2015.
 */
public class SoundManager {

    private Map<String, Sound> sounds;

    public static void load(AssetManager assetManager){
        assetManager.load("effects/HalfCircle.png", Texture.class);
        assetManager.load("effects/LightningSegment.png", Texture.class);
        assetManager.load("effects/Pixel.png", Texture.class);
        assetManager.load("lighttest/light3.png", Texture.class);
        assetManager.load("effects/shadow.png", Texture.class);
        assetManager.load("effects/shadow16.png", Texture.class);

    }
    public void assignResource(AssetManager assetManager){


    }

    public void unload(AssetManager assetManager){
        assetManager.unload("effects/HalfCircle.png");
        assetManager.unload("effects/LightningSegment.png");
        assetManager.unload("effects/Pixel.png");
        assetManager.unload("lighttest/light3.png");
        assetManager.unload("effects/shadow.png");
        assetManager.unload("effects/shadow16.png");
    }
    public SoundManager() {
        sounds = new HashMap<>();
    }

    public void play(String key) {
        sounds.get(key).play();
    }

    public void play(String key, float volume) {
        sounds.get(key).play(volume);
    }

    public void play(String key, float volume, float pitch) {
        sounds.get(key).play(volume, pitch, 1.0f);
    }
    public void play(String key, float volume, float pitch, float pan) {
        sounds.get(key).play(volume, pitch, pan);
    }
    public void resume() {

    }

    public void pause() {
    }

    public void dispose() {

    }

    // pan method, pans over a duration for a given sound pan("fireball", 5.0f);
}
