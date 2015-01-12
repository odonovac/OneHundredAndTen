package com.example.odonovac.onehundredandten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.odonovac.onehundredandten.adapters.EnterBidAdapter;
import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;
import com.example.odonovac.onehundredandten.layouts.CheckableLinearLayout;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class EnterBid extends Activity {

    private static ListView playersLV;

    Integer bidAmount = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bid);
        playersLV = (ListView)findViewById(android.R.id.list);
        playersLV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        playersLV.setItemsCanFocus(false);
        final Button nextBtn  = (Button) findViewById(R.id.bidNextBtn);
        ImageButton addTrickImgBtn = (ImageButton) findViewById(R.id.imageButtonPlus);
        ImageButton minusTrickImgBtn = (ImageButton) findViewById(R.id.imageButtonMinus);
        final TextView textPlayerBid = (TextView) findViewById(R.id.playerBid);
        final ArrayList<PlayerBean> listPlayers = ((MyApplication)getApplication()).getPlayers();
        final EnterBidAdapter playerAdapter = new EnterBidAdapter(getApplicationContext(), this, listPlayers);

        playersLV.setAdapter(playerAdapter);

        /*playersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final SparseBooleanArray checkedItems =  playersLV.getCheckedItemPositions();
                final StringBuffer sb = new StringBuffer("Selection: ");
                boolean isFirstSelected = true;
                final int checkedItemsCount = checkedItems.size();
                for (int j = 0; j < checkedItemsCount; ++j) {
                    // This tells us the item position we are looking at
                    // --
                    final int position = checkedItems.keyAt(j);

                    // This tells us the item status at the above position
                    // --
                    final boolean isChecked = checkedItems.valueAt(j);

                    if (isChecked) {
                        if (!isFirstSelected) {
                            sb.append(", ");
                        }
                        sb.append(position);
                        isFirstSelected = false;
                    }
                }

                // Show a message with the countries that are selected
                // --
                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();

            }
        });
*/


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

    public static void playerSelected(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        System.out.println("pos: " + holder.position);
        CheckBox chk = (CheckBox) v;
        playersLV.setItemChecked(holder.position, chk.isChecked());
    }

    public static class ViewHolder{
        public RadioButton     radioPlayer;
        //public TextView        textPlayerTotalScore;
        public ImageView imgPlayerTotalScore;
        public ImageView imgDealer;
        public int position;
        public CheckableLinearLayout layout;
    }

}
