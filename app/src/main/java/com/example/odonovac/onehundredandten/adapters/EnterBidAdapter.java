package com.example.odonovac.onehundredandten.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class EnterBidAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;



    public EnterBidAdapter(Context context, ArrayList<PlayerBean> players) {
        super(context, R.layout.enter_bid_row, players);
        this.context = context;
        this.players = players;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.enter_bid_row, parent, false);
        TextView textPlayerName = (TextView) rowView.findViewById(R.id.playerName);

        textPlayerName.setText(players.get(position).getPlayerName());
        return rowView;
    }
}
