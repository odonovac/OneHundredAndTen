package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
        final RadioGroup players = (RadioGroup)findViewById(R.id.bidRadioGroup);
        final Button nextBtn  = (Button) findViewById(R.id.bidNextBtn);
        final ArrayList<PlayerBean> listPlayers = getIntent().getParcelableArrayListExtra("players");
        NumberPicker numberPickerBid = (NumberPicker)findViewById(R.id.numberPickerBid);
    
        final RadioButton[] rb = new RadioButton[listPlayers.size()];
        for(int i=0; i<rb.length; i++){
            rb[i]  = new RadioButton(this);
            rb[i].setText(listPlayers.get(i).getPlayerName());
            rb[i].setId(i);
            players.addView(rb[i]);
        }

        int n=0;
        for(int i=0; i<6; i++){
            stringArray[i] = Integer.toString(n);
            n+=5;
        }
        numberPickerBid.setMaxValue(5);
        numberPickerBid.setMinValue(0);
        numberPickerBid.setDisplayedValues(stringArray);
        numberPickerBid.setWrapSelectorWheel(true);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EnterBid.class);
                intent.putParcelableArrayListExtra("players", listPlayers);
                startActivity(intent);
            }
        });

    }
}
