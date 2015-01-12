package com.example.odonovac.onehundredandten.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.KeepScores;
import com.example.odonovac.onehundredandten.MyApplication;
import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class KeepScoresAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;
    private final ArrayList<TeamBean> teams;
    private final Activity activity;

    public KeepScoresAdapter(Context context, Activity activity, ArrayList<PlayerBean> values) {
        super(context, R.layout.keep_scores_row, values );
        this.context = context;
        this.players = values;
        this.activity = activity;
        this.teams = ((MyApplication)activity.getApplication()).getTeams();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.keep_scores_row, parent, false);
        TextView textPlayerName = (TextView) rowView.findViewById(R.id.playerName);
        TextView textPlayerScore = (TextView) rowView.findViewById(R.id.playerScore);
        //TextView textPlayerTotalScore = (TextView)rowView.findViewById(R.id.playerTotalScore);

        ImageView imgBidder = (ImageView)rowView.findViewById(R.id.imgBidder);
        ImageView imgDealer = (ImageView)rowView.findViewById(R.id.imgDealer);
        ImageView imgPlayerTotalScore = (ImageView) rowView.findViewById(R.id.imgPlayerTotalScore);

        ImageButton addTrickImgBtn = (ImageButton) rowView.findViewById(R.id.imageButtonPlus);
        ImageButton minusTrickImgBtn = (ImageButton) rowView.findViewById(R.id.imageButtonMinus);
        textPlayerName.setText(players.get(position).getPlayerName());
        textPlayerScore.setText(players.get(position).getPlayerScoreText());
        if(((MyApplication)activity.getApplication()).getGameMode() == MyApplication.SINGLE) {
            //textPlayerTotalScore.setText(players.get(position).getPlayerTotalScoreText());
            Bitmap bmp= BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
            int width=bmp.getWidth()/23;
            int height=bmp.getHeight()/2;

            Bitmap resizedBitmap=Bitmap.createBitmap(bmp,((players.get(position).getPlayerTotalScore()/5)*width),0, width, height);
            imgPlayerTotalScore.setImageBitmap(resizedBitmap);
        }
        else
        {
            //textPlayerTotalScore.setText(teams.get(players.get(position).getTeamID() -1).getTeamTotalScoreText());
        }

        if(!players.get(position).isBidder())
            imgBidder.setVisibility(View.INVISIBLE);
        else
        {
            Bitmap bmp= BitmapFactory.decodeResource(context.getResources(), R.drawable.chips_all);
            int width=100;
            int height=bmp.getHeight();

            Bitmap resizedBitmap=Bitmap.createBitmap(bmp,((players.get(position).getBid()/5)*width),0, width, height);
            imgBidder.setImageBitmap(resizedBitmap);
        }

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
