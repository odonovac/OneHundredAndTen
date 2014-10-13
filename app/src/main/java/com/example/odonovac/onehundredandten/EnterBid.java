package com.example.odonovac.onehundredandten;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.odonovac.onehundredandten.adapters.EnterBidAdapter;
import com.example.odonovac.onehundredandten.beans.PlayerBean;

import java.util.ArrayList;

/**
 * Created by odonovac on 08/10/2014.
 */
public class EnterBid extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bid);
        String[] stringArray = new String[6];
        final ListView players = (ListView)findViewById(android.R.id.list);
        final NumberPicker playerBid = (NumberPicker) findViewById(R.id.numberPickerBid);
        ArrayList<PlayerBean> playerNames = getIntent().getParcelableArrayListExtra("players");
        NumberPicker numberPickerBid = (NumberPicker)findViewById(R.id.numberPickerBid);
        final EnterBidAdapter bidAdapter = new EnterBidAdapter(getApplicationContext(), playerNames);

        players.setAdapter(bidAdapter);

        //setListAdapter(bidAdapter);
        players.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        int n=0;
        for(int i=0; i<6; i++){
            stringArray[i] = Integer.toString(n);
            n+=5;
        }
        numberPickerBid.setMaxValue(5);
        numberPickerBid.setMinValue(0);
        numberPickerBid.setDisplayedValues(stringArray);
        numberPickerBid.setWrapSelectorWheel(true);

        numberPickerBid.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //players.get(position).setBid(newVal);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,
                String.valueOf(getListView().getCheckedItemCount()),
                Toast.LENGTH_LONG).show();
        return true;
    }


}
