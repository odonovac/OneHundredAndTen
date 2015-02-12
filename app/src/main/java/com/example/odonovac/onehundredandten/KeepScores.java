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
import com.example.odonovac.onehundredandten.beans.TeamBean;

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
        final ListView playersLV = (ListView)findViewById(android.R.id.list);

        final ArrayList<PlayerBean> players = ((MyApplication)getApplication()).getPlayers();//getIntent().getParcelableArrayListExtra("players");
        final ArrayList<TeamBean> teams = ((MyApplication)getApplication()).getTeams(); //getIntent().getParcelableArrayListExtra("teams");
        final KeepScoresAdapter playerAdapter = new KeepScoresAdapter(getApplicationContext(), this, players);

        playersLV.setAdapter(playerAdapter);

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

                    String winnerName = "";

                    if(((MyApplication)getApplication()).getGameMode() == MyApplication.SINGLE) {
                        playerloop:
                        for (PlayerBean player : players) {

                            //if this returns true, GAME OVER
                            if (player.updateScore()) {
                                gameOver = true;
                                winnerName = player.getPlayerName();
                                break playerloop;
                            }
                            player.setBidder(false);
                            player.setPlayerRoundScore(0);
                            if (setDealer) {
                                player.setDealer(true);
                                setDealer = false;
                            } else if (player.isDealer()) {
                                setDealer = true;
                                player.setDealer(false);
                            }
                        }
                    }
                    else{
                        teamloop:
                        for (TeamBean team : teams) {

                            //if this returns true, GAME OVER
                            if (team.updateScore()) {
                                gameOver = true;
                                winnerName = team.getTeamPlayersName();
                                break teamloop;
                            }
                        }
                        for (PlayerBean player : players) {
                            player.setBidder(false);
                            player.setPlayerRoundScore(0);
                            if (setDealer) {
                                player.setDealer(true);
                                setDealer = false;
                            } else if (player.isDealer()) {
                                setDealer = true;
                                player.setDealer(false);
                            }
                        }
                    }

                    if(setDealer)
                        players.get(0).setDealer(true);
                    scoreRunningTotal = 0;
                    //redirect player to summary screen
                    Intent intent;
                    if(!gameOver) {
                        intent = new Intent(getApplicationContext(), EnterBid.class);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), GameOver.class);
                        intent.putExtra("winnerName",winnerName);
                    }
                    startActivity(intent);
                }
            }
        });






    }

}
