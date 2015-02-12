package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by odonovac on 18/10/2014.
 */
public class GameOver extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        TextView winners = (TextView)findViewById(R.id.winner);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("winnerName");

        winners.setText(message + "  won!!!!");


    }
}
