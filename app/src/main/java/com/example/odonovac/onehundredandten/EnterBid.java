package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.odonovac.onehundredandten.adapters.EnterBidAdapter;
import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class EnterBid extends Activity {

    Integer bidAmount = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bid);
        final ListView playersLV = (ListView)findViewById(android.R.id.list);
        playersLV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        playersLV.setItemsCanFocus(false);
        final Button nextBtn  = (Button) findViewById(R.id.bidNextBtn);
        ImageButton addTrickImgBtn = (ImageButton) findViewById(R.id.imageButtonPlus);
        ImageButton minusTrickImgBtn = (ImageButton) findViewById(R.id.imageButtonMinus);
        final TextView textPlayerBid = (TextView) findViewById(R.id.playerBid);
        final ArrayList<PlayerBean> listPlayers = ((MyApplication)getApplication()).getPlayers();
        final EnterBidAdapter playerAdapter = new EnterBidAdapter(getApplicationContext(), this, listPlayers);

        playersLV.setAdapter(playerAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selected = playersLV.getCheckedItemPosition();
                if (selected<0) {
                    Toast.makeText(getApplicationContext(),
                            "Select the bidder to continue", Toast.LENGTH_SHORT).show();
                }
                else {
                    listPlayers.get(selected).setBidder(true);
                    listPlayers.get(selected).setBid(bidAmount);
                    Intent intent = new Intent(getApplicationContext(), KeepScores.class);
                    startActivity(intent);
                }
            }
        });

        addTrickImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bidAmount<29) {
                    bidAmount += 5;
                    textPlayerBid.setText(bidAmount.toString());
                }
            }
        });

        minusTrickImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bidAmount>0) {
                    bidAmount -= 5;
                    textPlayerBid.setText(bidAmount.toString());
                }
            }
        });
    }
}
