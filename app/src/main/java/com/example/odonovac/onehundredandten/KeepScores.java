package com.example.odonovac.onehundredandten;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.odonovac.onehundredandten.adapters.KeepScoresAdapter;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class KeepScores extends ListActivity {

    public final static int MAX_ROUND_SCORE = 30;
    public static int scoreRunningTotal = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_scores);

        final ListView players = (ListView)findViewById(android.R.id.list);

        final ArrayList<PlayerBean> playerNames = getIntent().getParcelableArrayListExtra("players");

        final KeepScoresAdapter playerAdapter = new KeepScoresAdapter(getApplicationContext(), playerNames, this);

        players.setAdapter(playerAdapter);

        TextView score = (TextView)findViewById(R.id.availableScores);

        score.setText("" + (MAX_ROUND_SCORE - scoreRunningTotal));

        Button cont = (Button)findViewById(R.id.roundDone);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((MAX_ROUND_SCORE == scoreRunningTotal)) {
                    //run game logic to refresh scores
                    boolean setDealer = false;
                    boolean gameOver = false;
                    playerloop:
                    for(PlayerBean player : playerNames){
                        //if this returns true, GAME OVER
                        if(player.updateScore()){
                            gameOver=true;
                            break playerloop;
                        }

                        player.setBidder(false);
                        player.setPlayerRoundScore(0);
                        if(setDealer) {
                            player.setDealer(true);
                            setDealer = false;
                        }
                        else if(player.isDealer()) {
                            setDealer = true;
                            player.setDealer(false);
                        }
                    }
                    if(setDealer)
                        playerNames.get(0).setDealer(true);
                    scoreRunningTotal = 0;
                    //redirect player to summary screen
                    Intent intent;
                    if(!gameOver) {
                        intent = new Intent(getApplicationContext(), EnterBid.class);
                        intent.putParcelableArrayListExtra("players", playerNames);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), GameOver.class);
                    }
                    startActivity(intent);
                }
            }
        });






    }

}
