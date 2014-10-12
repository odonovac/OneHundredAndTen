package com.example.odonovac.onehundredandten.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.CreatePlayers;
import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class CreatePlayerAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;
    private final CreatePlayers activity;

    public CreatePlayerAdapter(Context context, ArrayList<PlayerBean>  players, CreatePlayers activity) {
        super(context, R.layout.create_players_row, players);
        this.context = context;
        this.players = players;
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.create_players_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.playerName);
        ImageButton deleteBtn = (ImageButton) rowView.findViewById(R.id.deleteBtn);
        ImageButton dealerBtn = (ImageButton) rowView.findViewById(R.id.dealerBtn);
        textView.setText(players.get(position).getPlayerName());
        // Change the icon for Windows and iPhone
        if (players.get(position).isDealer()) {
            dealerBtn.setImageResource(R.drawable.dealer_selected);
        }
        else
        {
            dealerBtn.setImageResource(R.drawable.dealer);
        }

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context,"Cat sucks this many times: " + position, Toast.LENGTH_SHORT).show();
                players.remove(position);
                notifyDataSetChanged();
            }
        });
        dealerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < players.size(); i++) {
                    if(i != position)
                        players.get(i).setDealer(false);
                }
                players.get(position).toggleDealer();

                Button done = (Button) activity.findViewById(R.id.doneButton);
                done.setEnabled(false);

                for(PlayerBean player:players) {
                    if (player.isDealer())
                        done.setEnabled(true);
                }
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
