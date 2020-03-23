package fr.iut.kiwininjaclicker;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

public class MusicManager {

    public static MediaPlayer sound;
    private static MusicManager refrence = null;

    public static MusicManager getInstance(){
        if(refrence == null){
            refrence = new MusicManager ();
        }
        return refrence;
    }

    public void initalizeMediaPlayer(Context context, int musicId){
        sound = MediaPlayer.create(context, musicId);
    }

    public void startPlaying(){
        sound.setLooping(true);
        sound.start();    }

    public void stopPlaying(){
        sound.stop();
        sound.prepareAsync();
    }
}
