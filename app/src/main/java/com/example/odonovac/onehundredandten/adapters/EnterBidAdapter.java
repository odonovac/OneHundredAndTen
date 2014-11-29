package com.example.odonovac.onehundredandten.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.MyApplication;
import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class EnterBidAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;
    private final ArrayList<TeamBean> teams;
    private final Activity activity;
    private RadioButton mSelectedRB;
    private int mSelectedPosition = -1;

    public EnterBidAdapter(Context context, Activity activity, ArrayList<PlayerBean> values) {
        super(context, R.layout.enter_bid_row, values );
        this.context = context;
        this.players = values;
        this.activity = activity;
        this.teams = ((MyApplication)activity.getApplication()).getTeams();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.enter_bid_row, null);
            holder = new ViewHolder();
            holder.radioPlayer = (RadioButton)view.findViewById(R.id.rbPlayerName);
            holder.textPlayerTotalScore = (TextView)view.findViewById(R.id.playerTotalScore);
            holder.imgDealer = (ImageView)view.findViewById(R.id.imgDealer);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        final PlayerBean player = players.get(position);

        holder.radioPlayer.setText(player.getPlayerName());
        if(((MyApplication)activity.getApplication()).getGameMode() == MyApplication.SINGLE) {
            holder.textPlayerTotalScore.setText(players.get(position).getPlayerTotalScoreText());
        }
        else
        {
            holder.textPlayerTotalScore.setText(teams.get(players.get(position).getTeamID() -1).getTeamTotalScoreText());
        }
        if(!players.get(position).isDealer())
            holder.imgDealer.setVisibility(View.INVISIBLE);

        holder.radioPlayer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(position != mSelectedPosition && mSelectedRB != null){
                    mSelectedRB.setChecked(false);
                }
                mSelectedPosition = position;
                mSelectedRB = (RadioButton)v;
            }
        });

        if(mSelectedPosition != position){
            holder.radioPlayer.setChecked(false);
        }else{
            holder.radioPlayer.setChecked(true);
            if(mSelectedRB != null && holder.radioPlayer != mSelectedRB){
                mSelectedRB = holder.radioPlayer;
            }
        }

        return view;
    }
    private class ViewHolder{
        RadioButton     radioPlayer;
        TextView        textPlayerTotalScore;
        ImageView       imgDealer;
    }
}
