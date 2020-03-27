package fr.iut.kiwininjaclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout;
    private TextView nbrClick;
    private int bonus;
    Handler handler;
    private AtomicBoolean isThreadRunnning = new AtomicBoolean();
    private AtomicBoolean isThreadPausing = new AtomicBoolean();
    AtomicBoolean isPausing = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.mainLayout);
        nbrClick = findViewById(R.id.nbrClick);
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        nbrClick.setText(prefs.getString("BANANE", String.valueOf(0)));
        bonus = prefs.getInt("BONUS", 0);
        if (bonus == 0) {
            bonus = 1;
        }
        handler = new HandlerIncrementation(nbrClick);
        MusicManager.getInstance().initalizeMediaPlayer(getBaseContext(), R.raw.katana); // to initalize of media player
        if (prefs.getBoolean("MUSIQUE", true))
            MusicManager.getInstance().startPlaying();// to start playing music



        Thread background = new Thread(new Runnable() {
            /**
             * Le Bundle qui porte les données du Message et sera transmis au Handler
             */
            Bundle messageBundle = new Bundle();
            /**
             * Le message échangé entre la Thread et le Handler
             */
            Message myMessage;

            // Surcharge de la méthode run
            public void run() {
                try {
                    while (isThreadRunnning.get()) {
                        if (isThreadPausing.get()) {
                            // Faire une pause cohérente avec le traitement, soulager le CPU
                            Thread.sleep(1000);
                        } else {
                            // Faire un traitement
                            Thread.sleep(1000);
                            // Obtenir le Message
                            myMessage = handler.obtainMessage();
                            //Ajouter des données à transmettre au Handler via le Bundle
                            messageBundle.putInt(HandlerIncrementation.NOMBRE_CLICK_INCREMENTE, bonus);
                            //Ajouter le Bundle au message
                            myMessage.setData(messageBundle);
                            // envoyer le message au Hanlder
                            handler.sendMessage(myMessage);
                        }
                    }
                } catch (Throwable t) {
                    // Termine la Thread
                }
            }
        });
        isThreadRunnning.set(true);
        //Lancer la Thread
        background.start();

    }

    @Override
    protected void onDestroy() {
        // Tuer la Thread
        isThreadRunnning.set(false);
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs",
                MODE_PRIVATE).edit();
        editor.putString("BANANE", nbrClick.getText().toString());
        editor.putInt("BONUS", bonus);
        editor.apply();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        isThreadPausing.set(true);
        super.onPause();
    }

    @Override
    protected void onResume() {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        nbrClick.setText(prefs.getString("BANANE", String.valueOf(0)));
        bonus = prefs.getInt("BONUS", 0);
        isThreadPausing.set(false);
        super.onResume();
    }

    public void pushButton(View v) {
        incrementNbrClick(1);
    }

    public void incrementNbrClick(float i) {
        int n = Integer.parseInt(nbrClick.getText().toString());
        n += i;
        Log.d("value : ", String.valueOf(n));
        nbrClick.setText(String.valueOf(n));
    }

    public void paramOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, Parametre.class);
        intent.putExtra("NBRBANANES", Integer.parseInt(nbrClick.getText().toString()));
        startActivity(intent);
    }

    public void upgradeONClick(View v) {
        Intent intent = new Intent(MainActivity.this, Shop.class);
        intent.putExtra("BONUS", bonus);
        intent.putExtra("NBRBANANES", Integer.parseInt(nbrClick.getText().toString()));
        startActivity(intent);
    }


}