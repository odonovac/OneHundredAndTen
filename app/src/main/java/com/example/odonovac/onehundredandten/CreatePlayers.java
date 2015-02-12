package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.odonovac.onehundredandten.adapters.CreatePlayerAdapter;
import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 05/10/2014.
 */
public class CreatePlayers extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_players);

        final int gameMode = ((MyApplication)getApplication()).getGameMode();

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("Message");
// Set the Icon for the Dialog



        final EditText playerName = (EditText) findViewById(R.id.playerName);
        final ImageButton info = (ImageButton) findViewById(R.id.infoButton);
        final Button add = (Button) findViewById(R.id.addPlayer);
        final Button done = (Button) findViewById(R.id.doneButton);
        add.setEnabled(false);
        done.setEnabled(false);

        final ListView players = (ListView) findViewById(android.R.id.list);

        final ArrayList<PlayerBean> listPlayers = ((MyApplication)getApplication()).getPlayers();
        final CreatePlayerAdapter playerAdapter = new CreatePlayerAdapter(getApplicationContext(), listPlayers, this);

        players.setAdapter(playerAdapter);

        playerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (playerName.getText().toString().length() > 0) add.setEnabled(true);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameMode == MyApplication.SINGLE && listPlayers.size() < 6 || (gameMode == MyApplication.TEAM && listPlayers.size() < 6)) {
                    int teamID = getNextTeamID(listPlayers.size());
                    listPlayers.add(new PlayerBean(playerName.getText().toString(), 0, teamID));
                    playerName.setText("");
                    view.setEnabled(false);
                    if ((listPlayers.size() > 1) && dealerEnabled(listPlayers))
                        done.setEnabled(true);
                    else
                        done.setEnabled(false);
                    playerAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No more than 6 players in team mode.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EnterBid.class);
                if(((MyApplication)getApplication()).getGameMode()==MyApplication.TEAM)
                    ((MyApplication)getApplication()).createTeams();
                startActivity(intent);
            }
        });

    }

    private boolean dealerEnabled(ArrayList<PlayerBean> listPlayers) {
        for (PlayerBean player: listPlayers)
        {
            if(player.isDealer())
                return true;
        }
        return false;
    }

    private int getNextTeamID(int listPlayersSize) {
        int nextTeamID = 1;
        if (listPlayersSize >= 1)
        {
            if (((listPlayersSize == 1)||(listPlayersSize == 4))){
                nextTeamID = 2;
            }
            else if ((listPlayersSize == 2) || (listPlayersSize == 5)){
                nextTeamID = 3;
            }
            else if (listPlayersSize == 3){
                nextTeamID = 1;
            }
        }
        return nextTeamID;
    }

}

