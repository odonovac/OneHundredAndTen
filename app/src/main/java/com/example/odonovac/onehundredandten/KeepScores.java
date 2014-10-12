package com.example.odonovac.onehundredandten;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.odonovac.onehundredandten.adapters.KeepScoresAdapter;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class KeepScores extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_scores);

        final ListView players = (ListView)findViewById(android.R.id.list);

        ArrayList<PlayerBean> playerNames = getIntent().getParcelableArrayListExtra("players");

        final KeepScoresAdapter playerAdapter = new KeepScoresAdapter(getApplicationContext(), playerNames);

        players.setAdapter(playerAdapter);


    }

}
