package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by odonovac on 05/10/2014.
 */
public class CreatePlayers extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_players);

        final EditText playerName = (EditText)findViewById(R.id.playerName);
        Button add = (Button)findViewById(R.id.addPlayer);
        final ListView players = (ListView)findViewById(R.id.players);

        final ArrayList<String> listPlayers = new ArrayList<String>();

        final ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listPlayers);

        players.setAdapter(playerAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPlayers.add(playerName.getText().toString());
                playerAdapter.notifyDataSetChanged();

            }
        });

        Button done = (Button)findViewById(R.id.doneButton);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KeepScores.class);
                intent.putStringArrayListExtra("playerNames", listPlayers);
                startActivity(intent);
            }
        });
        //blah

    }

}
