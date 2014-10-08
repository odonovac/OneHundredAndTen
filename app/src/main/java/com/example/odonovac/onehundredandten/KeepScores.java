package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class KeepScores extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_scores);

        ArrayList<String> playerNames = getIntent().getStringArrayListExtra("playerNames");

    }

}
