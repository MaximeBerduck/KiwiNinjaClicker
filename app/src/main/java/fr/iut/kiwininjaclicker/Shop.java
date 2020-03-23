package fr.iut.kiwininjaclicker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop extends AppCompatActivity {
    private HashMap<Integer, ItemShop> listeAmelioration;
    private ListView listeAmeliorationsLayout;
    private TextView titreBoutique;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        listeAmeliorationsLayout = (ListView) findViewById(R.id.listeAmeliorations);
        titreBoutique = (TextView) findViewById(R.id.titreBoutique);
        listeAmelioration = new HashMap<>();

        List<ItemShop> listeItem = genererItemShop();
        ItemShopAdapter itemShopAdapter = new ItemShopAdapter(Shop.this, listeItem);
        listeAmeliorationsLayout.setAdapter(itemShopAdapter);

        listeAmeliorationsLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                titreBoutique.setText(selectedItem);
                // Display the selected item text on TextView
                Log.d("tag", "Your favorite : " + selectedItem);
            }
        });
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

    public void onClickDoSomething(View view) {
        // the view is the line you have clicked on
        Log.d("TAG", "onClickDoSomething: " + view);
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
