package fr.iut.kiwininjaclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private TextView titreBoutique;
    private int bonus;
    private int nbrBananes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        Intent intent = getIntent();
        bonus = intent.getIntExtra("BONUS", 1);
        nbrBananes = intent.getIntExtra("NBRBANANES", 0);

        listeAmeliorationsLayout = (ListView) findViewById(R.id.listeAmeliorations);
        titreBoutique = (TextView) findViewById(R.id.titreBoutique);

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
            list.add(new ItemShop("test", (float) 10000, (float) 10000, (float) 2));
            list.add(new ItemShop("test1", (float) 1, (float) 1, (float) 2));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 2));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
            list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        }
        return list;
    }

    public void augmenterBonus(int augmentation, int prixAugmentation) {
        bonus += augmentation;
        nbrBananes -= prixAugmentation;
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
