package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class EnterBid extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bid);
        String[] stringArray = new String[6];
        final RadioGroup playersRG = (RadioGroup)findViewById(R.id.bidRadioGroup);
        final Button nextBtn  = (Button) findViewById(R.id.bidNextBtn);
        final ArrayList<PlayerBean> listPlayers = getIntent().getParcelableArrayListExtra("players");
        final NumberPicker bidNumberPicker = (NumberPicker)findViewById(R.id.bidNumberPicker);
        final RadioButton[] rb = new RadioButton[listPlayers.size()];

        for(int i=0; i<rb.length; i++){
            rb[i]  = new RadioButton(this);
            rb[i].setText(listPlayers.get(i).getPlayerName());
            rb[i].setId(i);
            playersRG.addView(rb[i]);
        }

        int n=0;
        for(int i=0; i<6; i++){
            stringArray[i] = Integer.toString(n);
            n+=5;
        }
        bidNumberPicker.setMaxValue(5);
        bidNumberPicker.setMinValue(0);
        bidNumberPicker.setDisplayedValues(stringArray);
        bidNumberPicker.setWrapSelectorWheel(true);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = playersRG.getCheckedRadioButtonId();
                if (selected<0) {
                    Toast.makeText(getApplicationContext(),
                            "Select the bidder to continue", Toast.LENGTH_SHORT).show();
                }
                else {
                    listPlayers.get(selected).setBidder(true);
                    listPlayers.get(selected).setBid(bidNumberPicker.getValue());
                    Intent intent = new Intent(getApplicationContext(), KeepScores.class);
                    intent.putParcelableArrayListExtra("players", listPlayers);
                    startActivity(intent);
                }
            }
        });

    }
}
