package fr.iut.kiwininjaclicker;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Parametre extends AppCompatActivity {

    private ImageButton imageSon;
    private ImageButton imageMusique;
    boolean flagSon=true;
    boolean flagMusique=true;
    private MediaPlayer sound;


    @Override
    public void onCreate(Bundle v) {
        super.onCreate(v);
        setContentView(R.layout.parametre);

         imageSon =  findViewById(R.id.imageSon);
         imageMusique =  findViewById(R.id.imageMusique);

    }


    public void muteSon(View v) {
        if (!flagSon) {
            MusicManager.getInstance().startPlaying();// to start playing music
            imageSon.setImageResource(R.drawable.son_foreground);
            flagSon=true;
        }
        else {
            MusicManager.getInstance().stopPlaying();// to start playing music
            imageSon.setImageResource(R.drawable.sonmuet_foreground);
            flagSon=false;
        }
    }

    public void muteMusique(View v) {
        if (!flagMusique) {
            imageMusique.setImageResource(R.drawable.musique_foreground);
            flagMusique=true;
        }
        else {
            imageMusique.setImageResource(R.drawable.musiquemuet_foreground);
            flagMusique=false;
        }
    }

    public void retour(View v) {
       onBackPressed();
    }

}