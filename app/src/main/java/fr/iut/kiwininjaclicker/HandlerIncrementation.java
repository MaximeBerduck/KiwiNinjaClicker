package fr.iut.kiwininjaclicker;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class HandlerIncrementation extends Handler {
    private TextView nbrClick;
    public static final String NOMBRE_CLICK_INCREMENTE = "nbrClickIncr";

    public HandlerIncrementation(TextView tv) {
        this.nbrClick = tv;
    }

    @Override
    public void handleMessage(Message msg) {
        int v = Integer.valueOf(nbrClick.getText().toString()) + msg.getData().getInt(NOMBRE_CLICK_INCREMENTE);
        nbrClick.setText(String.valueOf(v));
    }
}
