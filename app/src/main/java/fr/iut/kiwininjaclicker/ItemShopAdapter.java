package fr.iut.kiwininjaclicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class ItemShopAdapter extends ArrayAdapter<ItemShop> {
    private final Context _context;
    private List<ItemShop> items;

    public ItemShopAdapter(@NonNull Context context, int ressource, @NonNull List<ItemShop> objects) {
        super(context, ressource, objects);
        items = objects;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shop, parent, false);
        } else {
            convertView = (ConstraintLayout) convertView;
        }

        ItemShopViewHolder viewHolder = (ItemShopViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ItemShopViewHolder();
            viewHolder.nomItem = (TextView) convertView.findViewById(R.id.nomItem);
            viewHolder.prixGain = (TextView) convertView.findViewById(R.id.prixGain);
            viewHolder.prixItem = (TextView) convertView.findViewById(R.id.prixItem);
            viewHolder.photoItem = (ImageView) convertView.findViewById(R.id.photoItem);
            viewHolder.buttonAcheter = (Button) convertView.findViewById(R.id.buttonAcheter);
            convertView.setTag(viewHolder);
        }
        Shop shop = (Shop) _context;
        Button btButton = (Button) convertView.findViewById(R.id.buttonAcheter);
        // Cache row position inside the button using `setTag`
        btButton.setTag(position);
        // Attach the click event handler
        final ItemShopViewHolder finalViewHolder = viewHolder;
        final View finalConvertView = convertView;
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shop shop = (Shop) _context;
                int position = (Integer) view.getTag();
                ItemShop item = getItem(position);
                Toast.makeText(_context.getApplicationContext(), item.getNomItem(), Toast.LENGTH_SHORT).show();

                shop.augmenterBonus(item.getGainAmelioration().intValue(), item.getPrixAmelioration().intValue());

                item.augmenterAmelioration();
                finalViewHolder.prixGain.setText(String.valueOf(item.getGainAmelioration()));
                finalViewHolder.prixItem.setText(String.valueOf(item.getPrixAmelioration()));
                if(shop.getNbrBananes()<item.getPrixAmelioration()){
                    Button btButton = (Button) finalConvertView.findViewById(R.id.buttonAcheter);
                    btButton.setEnabled(false);
                }
            }
        });

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ItemShop itemShop = getItem(position);

        if(shop.getNbrBananes()<itemShop.getPrixAmelioration())
            btButton.setEnabled(false);


            //il ne reste plus qu'à remplir notre vue
        viewHolder.nomItem.setText(itemShop.getNomItem());
        viewHolder.prixGain.setText(String.valueOf(itemShop.getGainAmelioration()));
        viewHolder.prixItem.setText(String.valueOf(itemShop.getPrixAmelioration()));
//        viewHolder.photoItem.setImage
//        viewHolder.buttonAcheter.set


        return convertView;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.item_shop, parent, false);
//        } else {
//            convertView = (ConstraintLayout) convertView;
//        }
//
//        Button btButton = (Button) convertView.findViewById(R.id.buttonAcheter);
//        // Cache row position inside the button using `setTag`
//        btButton.setTag(position);
//        // Attach the click event handler
//        btButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = (Integer) view.getTag();
//                // Access the row position here to get the correct data item
//                ItemShop item = getItem(position);
//                Toast.makeText(_context.getApplicationContext(), item.getNomItem(), Toast.LENGTH_SHORT).show();
//
//                // Do what you want here...
//            }
//        });
//
////        ImageView imageView = (ImageView) convertView.findViewById(R.id.cover);
////        imageView.setBackgroundResource(_movies.get(position).getCover());
//
//        TextView nomItem = (TextView) convertView.findViewById(R.id.nomItem);
//        nomItem.setText(items.get(position).getNomItem());
//        nomItem.setTag(items.get(position).getNomItem());
//
//        return convertView;
//    }
}
