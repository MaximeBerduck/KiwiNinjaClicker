package fr.iut.kiwininjaclicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

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
            convertView = (LinearLayout) convertView;
        }

        ItemShopViewHolder viewHolder = (ItemShopViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ItemShopViewHolder();
            viewHolder.nomItem = (TextView) convertView.findViewById(R.id.nomItem);
            viewHolder.prixGain = (TextView) convertView.findViewById(R.id.prixGain);
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

                if (shop.getNbrBananes() < item.getPrixAmelioration()) {
                    Button btButton = (Button) finalConvertView.findViewById(R.id.buttonAcheter);
                    btButton.setEnabled(false);
                } else {
                    shop.augmenterBonus(item.getGainAmelioration().intValue(), item.getPrixAmelioration().intValue());

                    item.augmenterAmelioration();
                    finalViewHolder.prixGain.setText(String.valueOf(item.getGainAmelioration()));
                    finalViewHolder.buttonAcheter.setText(String.valueOf(item.getPrixAmelioration()));
                    if (shop.getNbrBananes() < item.getPrixAmelioration()) {
                        Button btButton = (Button) finalConvertView.findViewById(R.id.buttonAcheter);
                        btButton.setEnabled(false);
                    }
                }
            }
        });

        //getItem(position) va récupérer l'item [position] de la List<ItemShop>
        ItemShop itemShop = getItem(position);

        if (shop.getNbrBananes() < itemShop.getPrixAmelioration()) {
            btButton.setEnabled(false);
        } else {
            btButton.setEnabled(true);
        }
        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomItem.setText(itemShop.getNomItem());
        viewHolder.prixGain.setText("+" + String.valueOf(itemShop.getGainAmelioration()));
        viewHolder.buttonAcheter.setText(String.valueOf(itemShop.getPrixAmelioration()));
        viewHolder.photoItem.setBackgroundResource(itemShop.getImage());

        return convertView;
    }
}
