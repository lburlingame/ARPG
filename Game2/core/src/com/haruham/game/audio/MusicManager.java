package com.haruham.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created on 9/29/2015.
 */
public class MusicManager {

    public Music music;
    public MusicManager() {
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/dkr2.mp3"));
        music.setLooping(true);
    }

    public void play() {
        music.play();
    }

    public void pause() {
        music.pause();
    }

    public void dispose() {
        music.dispose();
    }
    // pan method, pans over a duration for a given sound pan("fireball", 5.0f);
}
