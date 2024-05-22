package com.example.ninjawarrior;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    private static Music instance;
    private MediaPlayer music;

    public static  Music getInstance(){
        if(instance == null){
           return new Music();
        }
        return instance;
    }

    public Integer backgroundMain(){
       return R.raw.soundtuck;
    }

    public Integer backgroundGame(){
       return R.raw.background_game;
    }

    public Integer soundExplosion(){
        return R.raw.explosio;
    }

    public Integer soundGanivet(){
        return R.raw.llancament;
    }

    public void playMusic(Context context, int resId) {
        // Si hay música reproduciéndose, detenerla y liberarla
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
            music.release();
        }
        music = MediaPlayer.create(context, resId);
        music.start();
    }

    public void stopMusic(){
        music.stop();
    }

    // Método para obtener el objeto MediaPlayer actual
    public MediaPlayer getMusic() {
        return music;
    }
}
