package com.haruham.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created on 9/13/2015.
 */
public class SoundManager {


    public Sound music;
    public SoundManager() {
        music = Gdx.audio.newSound(Gdx.files.internal("audio/dkr.mp3"));

    }

    public void play() {
        music.loop(.6f, 1.2f, 0f);
    }

    public void resume() {
        music.resume();
    }

    public void pause() {
        music.pause();
    }

    public void dispose() {
        music.dispose();
    }
    // pan method, pans over a duration for a given sound pan("fireball", 5.0f);
}
