package fr.iut.kiwininjaclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Shop extends AppCompatActivity {
    private ArrayList<ItemShop> listeAmelioration;
    private ListView listeAmeliorationsLayout;
    private int bonus;
    private int nbrBananes;
    private TextView textNbrBananes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        Intent intent = getIntent();
        bonus = intent.getIntExtra("BONUS", 0);
        nbrBananes = intent.getIntExtra("NBRBANANES", 0);

        listeAmeliorationsLayout = (ListView) findViewById(R.id.listeAmeliorations);
        textNbrBananes = (TextView) findViewById(R.id.nbrBananesShop);
        textNbrBananes.setText(String.valueOf(nbrBananes));

        listeAmelioration = genererItemShop();
        ItemShopAdapter itemShopAdapter = new ItemShopAdapter(Shop.this, R.layout.item_shop, listeAmelioration);
        listeAmeliorationsLayout.setAdapter(itemShopAdapter);
    }

    private ArrayList<ItemShop> genererItemShop() {
        ArrayList<ItemShop> list;

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",
                MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Liste_ameliorations", null);
        Type type = new TypeToken<ArrayList<ItemShop>>() {
        }.getType();
        list = gson.fromJson(json, type);

        if (null == list) {
            list = new ArrayList<>();
            list.add(new ItemShop("Bâton", R.mipmap.batons_foreground, (float) 1, (float) 30, (float) 1.1));
            list.add(new ItemShop("Bombe fumigène", R.mipmap.bombe_foreground, (float) 5, (float) 100, (float) 1.5));
            list.add(new ItemShop("Protéines", R.mipmap.proteines_foreground, (float) 20, (float) 1000, (float) 1.5));
            list.add(new ItemShop("Shurikens", R.mipmap.shuriken_foreground, (float) 50, (float) 4000, (float) 1.5));
            list.add(new ItemShop("Nunchaku", R.mipmap.nunchaku_foreground, (float) 100, (float) 10000, (float) 1.5));
        }
        return list;
    }

    public void augmenterBonus(int augmentation, int prixAugmentation) {
        bonus += augmentation;
        nbrBananes -= prixAugmentation;
        textNbrBananes.setText(String.valueOf(nbrBananes));
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs",
                MODE_PRIVATE).edit();
        editor.putString("BANANE", String.valueOf(nbrBananes));
        editor.putInt("BONUS", bonus);
        Gson gson = new Gson();
        String json = gson.toJson(listeAmelioration);
        editor.remove("Liste_ameliorations");
        editor.putString("Liste_ameliorations", json);
        editor.apply();
    }

    public int getNbrBananes() {
        return this.nbrBananes;
    }

    public int getBonus() {
        return this.bonus;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs",
                MODE_PRIVATE).edit();
        editor.putString("BANANE", String.valueOf(nbrBananes));
        editor.putInt("BONUS", bonus);
        Gson gson = new Gson();
        String json = gson.toJson(listeAmelioration);
        editor.remove("Liste_ameliorations");
        editor.putString("Liste_ameliorations", json);
        editor.apply();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
