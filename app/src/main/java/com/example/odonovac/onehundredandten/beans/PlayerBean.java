package com.example.odonovac.onehundredandten.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by odonovac on 12/10/2014.
 */
public class PlayerBean implements Parcelable{

    private String playerName;
    private int playerRoundScore;
    private int playerTotalScore;
    private boolean dealer = false;
    private int bid;
    private boolean bidder = false;
    private int teamID;
    private boolean team = false;

    public PlayerBean(String name, int score, boolean team,  int teamID){
        this.playerName = name;
        this.playerRoundScore = score;
        this.team = team;
        this.teamID = teamID;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public int getPlayerRoundScore(){
        return this.playerRoundScore;
    }
    public int getPlayerTotalScore(){
        return this.playerTotalScore;
    }
    public int getBid(){
        return this.bid;
    }
    public int getTeamID(){
        return this.teamID;
    }
    public void setPlayerRoundScore(int score) {
        this.playerRoundScore = score;
    }
    public void setPlayerTotalScore(int score) {
        this.playerTotalScore = score;
    }

    public void setBid(int bid) {
        this.bid = bid*5;
    }
    public void setTeamID(int teamID) {
        this.teamID = teamID;
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

    public boolean isBidder(){
        return this.bidder;
    }
    public boolean isTeam(){
        return this.team;
    }

    public void setBidder(boolean bidder) {
        this.bidder = bidder;
    }
    public void setTeam(boolean team) {
        this.team = team;
    }
    public void toggleBidder() {
        this.bidder = !this.bidder;
    }
    public void toggleTeam() {
        this.team = !this.team;
    }
    public String getPlayerScoreText(){
        return String.valueOf(this.playerRoundScore);
    }

    public String getPlayerTotalScoreText(){
        return String.valueOf(this.playerTotalScore);
    }

    public String getBidText(){
        return String.valueOf(this.bid);
    }

    public void addTrick(){
        this.playerRoundScore += 5;
    }

    public void minusTrick(){
        this.playerRoundScore -= 5;
    }

    public boolean updateScore() {
        boolean gameOver = false;

        if(isBidder())
        {
           if(playerRoundScore >= bid) {
               playerTotalScore += playerRoundScore;
               if(playerTotalScore >= 110)
                   gameOver=true;
           }
            else
               playerTotalScore -= bid;
        }
        else {
            playerTotalScore += playerRoundScore;
            if(playerTotalScore > 110)
                playerTotalScore = 110;
        }

        return gameOver;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(playerName);
        parcel.writeInt(playerRoundScore);
        parcel.writeInt(playerTotalScore);
        parcel.writeInt(bid);
        parcel.writeInt(teamID);
        parcel.writeBooleanArray(new boolean[]{bidder, dealer,team});
    }

    private PlayerBean(Parcel in) {
        playerName = in.readString();
        playerRoundScore = in.readInt();
        playerTotalScore = in.readInt();
        bid = in.readInt();
        teamID = in.readInt();
        boolean[] boolArray = new boolean[3];
        in.readBooleanArray(boolArray);
        bidder = boolArray[0];
        dealer = boolArray[1];
        team = boolArray[2];
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
