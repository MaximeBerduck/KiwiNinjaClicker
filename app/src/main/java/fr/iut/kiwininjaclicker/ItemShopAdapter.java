package fr.iut.kiwininjaclicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ItemShopAdapter extends ArrayAdapter<ItemShop> {
    public ItemShopAdapter(@NonNull Context context, @NonNull List<ItemShop> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shop,parent, false);
        }

        ItemShopViewHolder viewHolder = (ItemShopViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ItemShopViewHolder();
            viewHolder.nomItem = (TextView) convertView.findViewById(R.id.nomItem);
            viewHolder.prixGain = (TextView) convertView.findViewById(R.id.prixGain);
            viewHolder.prixItem = (TextView) convertView.findViewById(R.id.prixItem);
            viewHolder.photoItem = (ImageView) convertView.findViewById(R.id.photoItem);
            viewHolder.buttonAcheter = (Button) convertView.findViewById(R.id.buttonAcheter);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ItemShop itemShop = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomItem.setText(itemShop.getNomItem());
        viewHolder.prixGain.setText(String.valueOf(itemShop.getGainAmelioration()));
        viewHolder.prixItem.setText(String.valueOf(itemShop.getPrixAmelioration()));
//        viewHolder.photoItem.setImage
//        viewHolder.buttonAcheter.set


        return convertView;
    }
}
