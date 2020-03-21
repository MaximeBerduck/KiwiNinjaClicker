package fr.iut.kiwininjaclicker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop extends AppCompatActivity {
    private HashMap<Integer, ItemShop> listeAmelioration;
    private ListView listeAmeliorationsLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        listeAmeliorationsLayout = (ListView) findViewById(R.id.listeAmeliorations);
        listeAmelioration = new HashMap<>();

        List<ItemShop> listeItem = genererItemShop();
        ItemShopAdapter itemShopAdapter = new ItemShopAdapter(Shop.this,listeItem);
        listeAmeliorationsLayout.setAdapter(itemShopAdapter);
    }

    private List<ItemShop> genererItemShop() {
        List<ItemShop> list = new ArrayList<ItemShop>();
        list.add(new ItemShop("test", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test1", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));
        list.add(new ItemShop("test2", (float) 1, (float) 1, (float) 1));

        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
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
