package com.example.odonovac.onehundredandten.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.EnterBid;
import com.example.odonovac.onehundredandten.MyApplication;
import com.example.odonovac.onehundredandten.R;
import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;
import com.example.odonovac.onehundredandten.layouts.CheckableLinearLayout;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class EnterBidAdapter extends ArrayAdapter<PlayerBean> {
    private final Context context;
    private final ArrayList<PlayerBean> players;
    private final ArrayList<TeamBean> teams;
    private final Activity activity;

    public EnterBidAdapter(Context context, Activity activity, ArrayList<PlayerBean> values) {
        super(context, R.layout.enter_bid_row, values );
        this.context = context;
        this.players = values;
        this.activity = activity;
        this.teams = ((MyApplication)activity.getApplication()).getTeams();
    }

    public int getCount() {
        return this.players.size();
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        EnterBid.ViewHolder holder;

        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.enter_bid_row, null);
            holder = new EnterBid.ViewHolder();
            holder.radioPlayer = (RadioButton)view.findViewById(R.id.rbPlayerName);
            //holder.textPlayerTotalScore = (TextView)view.findViewById(R.id.playerTotalScore);
            holder.imgPlayerTotalScore = (ImageView)view.findViewById(R.id.imgPlayerTotalScore);
            holder.imgDealer = (ImageView)view.findViewById(R.id.imgDealer);
            holder.position = position;
            holder.layout = (CheckableLinearLayout) view.findViewById(R.id.GridLayout1);
            view.setTag(holder);
        }else{
            holder = (EnterBid.ViewHolder)view.getTag();
        }
        final PlayerBean player = players.get(position);

        holder.radioPlayer.setText(player.getPlayerName());
        if(((MyApplication)activity.getApplication()).getGameMode() == MyApplication.SINGLE) {
            //holder.textPlayerTotalScore.setText(players.get(position).getPlayerTotalScoreText());
            Bitmap bmp= BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
            int width=bmp.getWidth()/23;
            int height=bmp.getHeight()/2;

            int score = players.get(position).getPlayerTotalScore();
            int yOffset = 0;
            if(score < 0) {
                score *= -1;
                yOffset = height;
            }

            Bitmap resizedBitmap=Bitmap.createBitmap(bmp,((score/5)*width),yOffset, width, height);
            holder.imgPlayerTotalScore.setImageBitmap(resizedBitmap);
        }
        else
        {
           // holder.textPlayerTotalScore.setText(teams.get(players.get(position).getTeamID() -1).getTeamTotalScoreText());

           int teamColour = R.drawable.green;

            int teamId = players.get(position).getTeamID();

            switch(teamId){
                case 1:
                    teamColour =  R.drawable.green;
                    break;
                case 2:
                    teamColour =  R.drawable.red;
                    break;
                case 3:
                    teamColour =  R.drawable.blue;
                    break;
            }

            Bitmap bmp= BitmapFactory.decodeResource(context.getResources(), teamColour);

            int width=bmp.getWidth()/23;
            int height=bmp.getHeight()/2;

            int score = players.get(position).getPlayerTotalScore();
            int yOffset = 0;
            if(score < 0) {
                score *= -1;
                yOffset = height;
            }

            Bitmap resizedBitmap=Bitmap.createBitmap(bmp,((score/5)*width),yOffset, width, height);
            holder.imgPlayerTotalScore.setImageBitmap(resizedBitmap);

        }
        if(!players.get(position).isDealer())
            holder.imgDealer.setVisibility(View.INVISIBLE);

        final ListView lv = (ListView) parent;
        holder.layout.setChecked(lv.isItemChecked(position));

       return view;
    }

}
