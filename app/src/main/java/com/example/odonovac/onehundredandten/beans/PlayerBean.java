package com.example.odonovac.onehundredandten.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by odonovac on 12/10/2014.
 */
public class PlayerBean implements Parcelable{

    private String playerName;
    private int playerScore;
    private boolean dealer = false;

    public PlayerBean(String name, int score){
        this.playerName = name;
        this.playerScore = score;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public int getPlayerScore(){
        return this.playerScore;
    }

    public void setPlayerScore(int score) {
        this.playerScore = score;
    }

    public boolean isDealer(){
        return this.dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public void toggleDealer() {
        this.dealer = !this.dealer;
    }
    public String getPlayerScoreText(){
        return String.valueOf(this.playerScore);
    }

    public void addTrick(){
        this.playerScore += 5;
    }

    public void minusTrick(){
        this.playerScore -= 5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(playerName);
        parcel.writeInt(playerScore);
    }

    private PlayerBean(Parcel in) {
        playerName = in.readString();
        playerScore = in.readInt();
    }

    public static final Parcelable.Creator<PlayerBean> CREATOR
            = new Parcelable.Creator<PlayerBean>() {
        public PlayerBean createFromParcel(Parcel in) {
            return new PlayerBean(in);
        }

        public PlayerBean[] newArray(int size) {
            return new PlayerBean[size];
        }
    };
}
