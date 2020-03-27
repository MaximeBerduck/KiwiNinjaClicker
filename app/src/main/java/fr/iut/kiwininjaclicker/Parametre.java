package fr.iut.kiwininjaclicker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.text.*;
import java.math.*;

import androidx.appcompat.app.AppCompatActivity;

public class Parametre extends AppCompatActivity {

    private ImageButton imageSon;
    private ImageButton imageMusique;
    boolean flagSon;
    boolean flagMusique;
    private MediaPlayer sound;
    private int nbrBananes;



    @Override
    public void onCreate(Bundle v) {
        super.onCreate(v);
        Intent intent = getIntent();
        nbrBananes = intent.getIntExtra("NBRBANANES", 0);
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        flagSon = prefs.getBoolean("SON", true);
        flagMusique = prefs.getBoolean("MUSIQUE", true);

        setContentView(R.layout.parametre);

        imageSon = findViewById(R.id.imageSon);
        if (flagSon) {
            UnMuteAudio();
            imageSon.setImageResource(R.drawable.son_foreground);
        } else {
            MuteAudio();
            imageSon.setImageResource(R.drawable.sonmuet_foreground);
        }


        imageMusique = findViewById(R.id.imageMusique);
        if (flagMusique) {
            MusicManager.getInstance().startPlaying();// to start playing music
            imageMusique.setImageResource(R.drawable.musique_foreground);
        } else {
            MusicManager.getInstance().stopPlaying();// to start playing music
            imageMusique.setImageResource(R.drawable.musiquemuet_foreground);
        }


    }

    public void MuteAudio(){
        AudioManager mAlramMAnager = (AudioManager)getSystemService(getBaseContext().AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
        } else {
            mAlramMAnager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            mAlramMAnager.setStreamMute(AudioManager.STREAM_ALARM, true);
            mAlramMAnager.setStreamMute(AudioManager.STREAM_RING, true);
            mAlramMAnager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        }
    }

    public void UnMuteAudio(){
        AudioManager mAlramMAnager = (AudioManager)getSystemService(getBaseContext().AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
            mAlramMAnager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
        } else {
            mAlramMAnager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
            mAlramMAnager.setStreamMute(AudioManager.STREAM_ALARM, false);
            mAlramMAnager.setStreamMute(AudioManager.STREAM_RING, false);
            mAlramMAnager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
        }
    }

    public void muteSon(View v) {
        if (!flagSon) {
            imageSon.setImageResource(R.drawable.son_foreground);
            flagSon = true;
            UnMuteAudio();
        } else {
            MuteAudio();
            imageSon.setImageResource(R.drawable.sonmuet_foreground);
            flagSon = false;
        }
    }

    public void muteMusique(View v) {
        if (!flagMusique) {
            MusicManager.getInstance().startPlaying();// to start playing music
            imageMusique.setImageResource(R.drawable.musique_foreground);
            flagMusique = true;
        } else {
            MusicManager.getInstance().stopPlaying();// to start playing music
            imageMusique.setImageResource(R.drawable.musiquemuet_foreground);
            flagMusique = false;
        }
    }

    public void retour(View v) {
        onBackPressed();
    }

    public void notation(View v) {
        NumberFormat formatter = new DecimalFormat("0.#####E0");
        System.out.println(nbrBananes);
        System.out.println(formatter.format(nbrBananes));
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs",
                MODE_PRIVATE).edit();
        editor.putBoolean("SON", flagSon);
        editor.putBoolean("MUSIQUE", flagMusique);
        editor.apply();
        super.onDestroy();
    }

}