package com.example.odonovac.onehundredandten.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.KeepScores;
import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class KeepScoresAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;
    private final Activity activity;

    public KeepScoresAdapter(Context context, ArrayList<PlayerBean>  values, Activity activity) {
        super(context, R.layout.keep_scores_row, values);
        this.context = context;
        this.players = values;
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.keep_scores_row, parent, false);
        TextView textPlayerName = (TextView) rowView.findViewById(R.id.playerName);
        TextView textPlayerScore = (TextView) rowView.findViewById(R.id.playerScore);
        TextView textPlayerTotalScore = (TextView)rowView.findViewById(R.id.playerTotalScore);

        ImageView imgBidder = (ImageView)rowView.findViewById(R.id.imgBidder);
        ImageView imgDealer = (ImageView)rowView.findViewById(R.id.imgDealer);

        ImageButton addTrickImgBtn = (ImageButton) rowView.findViewById(R.id.imageButtonPlus);
        ImageButton minusTrickImgBtn = (ImageButton) rowView.findViewById(R.id.imageButtonMinus);
        textPlayerName.setText(players.get(position).getPlayerName());
        textPlayerScore.setText(players.get(position).getPlayerScoreText());
        textPlayerTotalScore.setText(players.get(position).getPlayerTotalScoreText());

        if(!players.get(position).isBidder())
            imgBidder.setVisibility(View.INVISIBLE);

        if(!players.get(position).isDealer())
            imgDealer.setVisibility(View.INVISIBLE);

        addTrickImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(KeepScores.scoreRunningTotal < KeepScores.MAX_ROUND_SCORE) {
                    players.get(position).addTrick();
                    notifyDataSetChanged();
                    KeepScores.scoreRunningTotal+=5;
                    TextView txtView = (TextView)activity.findViewById(R.id.availableScores);
                    txtView.setText("" + (KeepScores.MAX_ROUND_SCORE - KeepScores.scoreRunningTotal));
                }
            }
        });

        minusTrickImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(players.get(position).getPlayerRoundScore() != 0) {
                    players.get(position).minusTrick();
                    notifyDataSetChanged();
                    KeepScores.scoreRunningTotal-=5;
                    TextView txtView = (TextView)activity.findViewById(R.id.availableScores);
                    txtView.setText("" + (KeepScores.MAX_ROUND_SCORE - KeepScores.scoreRunningTotal));
                }
            }
        });

        return rowView;
    }
}
