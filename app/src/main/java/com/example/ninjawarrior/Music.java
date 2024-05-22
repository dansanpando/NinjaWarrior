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

    public void backgroundMain(Context context){
        music = MediaPlayer.create(context,R.raw.soundtuck);
    }

    public void backgroundGame(Context context){
        music = MediaPlayer.create(context,R.raw.soundtuck);
    }

    public void soundExplosion(Context context){
        music = MediaPlayer.create(context,R.raw.explosio);
    }

    public void soundGanivet(Context context){
        music = MediaPlayer.create(context,R.raw.llancament);
    }

    public MediaPlayer getMusic() {
        return music;
    }
}
