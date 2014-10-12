package com.example.odonovac.onehundredandten.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class KeepScoresAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;

    public KeepScoresAdapter(Context context, ArrayList<PlayerBean>  values) {
        super(context, R.layout.keep_scores_row, values);
        this.context = context;
        this.players = values;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.keep_scores_row, parent, false);
        TextView textPlayerName = (TextView) rowView.findViewById(R.id.playerName);
        TextView textPlayerScore = (TextView) rowView.findViewById(R.id.playerScore);

        Button addTrickBtn = (Button) rowView.findViewById(R.id.addTrick);
        Button minusTrickBtn = (Button) rowView.findViewById(R.id.minusTrick);
        textPlayerName.setText(players.get(position).getPlayerName());
        textPlayerScore.setText(players.get(position).getPlayerScoreText());
        // Change the icon for Windows and iPhone
        addTrickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context,"Cat sucks this many times: " + position, Toast.LENGTH_SHORT).show();
                players.get(position).addTrick();
                notifyDataSetChanged();
            }
        });

        minusTrickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context,"Cat sucks this many times: " + position, Toast.LENGTH_SHORT).show();
                players.get(position).minusTrick();
                notifyDataSetChanged();
            }
        });


        return rowView;
    }
}
