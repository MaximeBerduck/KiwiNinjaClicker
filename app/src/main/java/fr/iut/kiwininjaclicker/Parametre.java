package fr.iut.kiwininjaclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Parametre extends AppCompatActivity {

    private ImageButton imageSon;
    private ImageButton imageMusique;
    boolean flagSon;
    boolean flagMusique;
    private MediaPlayer sound;


    @Override
    public void onCreate(Bundle v) {
        super.onCreate(v);
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        flagSon = prefs.getBoolean("SON", true);
        flagMusique = prefs.getBoolean("MUSIQUE", true);

        setContentView(R.layout.parametre);

        imageSon = findViewById(R.id.imageSon);
        if (flagSon) {
            MusicManager.getInstance().startPlaying();// to start playing music
            imageSon.setImageResource(R.drawable.son_foreground);
        } else {
            MusicManager.getInstance().stopPlaying();// to start playing music
            imageSon.setImageResource(R.drawable.sonmuet_foreground);
        }


        imageMusique = findViewById(R.id.imageMusique);
        if (flagMusique) {
            imageMusique.setImageResource(R.drawable.musique_foreground);
        } else {
            imageMusique.setImageResource(R.drawable.musiquemuet_foreground);
        }


    }


    public void muteSon(View v) {
        if (!flagSon) {
            MusicManager.getInstance().startPlaying();// to start playing music
            imageSon.setImageResource(R.drawable.son_foreground);
            flagSon = true;
        } else {
            MusicManager.getInstance().stopPlaying();// to start playing music
            imageSon.setImageResource(R.drawable.sonmuet_foreground);
            flagSon = false;
        }
    }

    public void muteMusique(View v) {
        if (!flagMusique) {
            imageMusique.setImageResource(R.drawable.musique_foreground);
            flagMusique = true;
        } else {
            imageMusique.setImageResource(R.drawable.musiquemuet_foreground);
            flagMusique = false;
        }
    }

    public void retour(View v) {
        onBackPressed();
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