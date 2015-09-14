package com.haruham.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created on 9/13/2015.
 */
public class SoundManager {


    public static Sound music = Gdx.audio.newSound(Gdx.files.internal("audio/dkr.mp3"));

    public SoundManager() {
    }

    public void play() {
        music.loop(.5f);
    }

    public void resume() {
        music.resume();
    }

    public void pause() {
        music.pause();
    }
}
