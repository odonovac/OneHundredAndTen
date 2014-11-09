package com.example.odonovac.onehundredandten.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by odonovac on 12/10/2014.
 */
public class TeamBean implements Parcelable{

    private String teamName;
    private int teamTotalScore;
    private int teamRoundScore;
    private int teamID;
    private ArrayList<PlayerBean> players;

    public TeamBean(){
        super();
    }

    public TeamBean(String name, int teamID, ArrayList<PlayerBean> players){
        super();
        this.teamName = name;
        this.teamID = teamID;
        this.players = players;
        for(PlayerBean player : players){
            this.teamTotalScore = player.getPlayerTotalScore();
            this.teamRoundScore += player.getPlayerRoundScore();
        };
    }

    public String getTeamName() {
        return this.teamName;
    }
    public void setTeamName(String name) {
        this.teamName = name;
    }
    public int getTeamTotalScore(){
        return this.teamTotalScore;
    }
    public int getTeamRoundScore(){
        return this.teamRoundScore;
    }
    public int getTeamID(){
        return this.teamID;
    }
    public void setTeamTotalScore(int score) {
        this.teamTotalScore = score;
    }
    public void setTeamRoundScore(int score) {
        this.teamRoundScore = score;
    }
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
    public String getTeamTotalScoreText(){
        return String.valueOf(this.teamTotalScore);
    }
    public String getTeamRoundScoreText(){
        return String.valueOf(this.teamRoundScore);
    }
    public boolean updateScore() {
        boolean gameOver = false;
        boolean isBidder = false;
        int bid = 0;
        for(PlayerBean player : players){
            this.teamRoundScore += player.getPlayerRoundScore();
            if (player.isBidder() && isBidder == false)
            {
                isBidder = true;
                bid = player.getBid();
            }
        };

        if(isBidder)
        {
           if(teamRoundScore >= bid) {
               teamTotalScore += teamRoundScore;
               if(teamTotalScore >= 110)
                   gameOver=true;
           }
            else
               teamTotalScore -= bid;
        }
        else {
            teamTotalScore += teamRoundScore;
            if(teamTotalScore > 110)
                teamTotalScore = 110;
        }

        for(PlayerBean player : players){
            player.setPlayerTotalScore(teamTotalScore);
        };
        return gameOver;
    }

    public void addPlayer(PlayerBean player){
        players.add(player);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(teamName);
        parcel.writeInt(teamTotalScore);
        parcel.writeInt(teamRoundScore);
        parcel.writeInt(teamID);
        parcel.writeList(players);
    }

    private TeamBean(Parcel in) {
        teamName = in.readString();
        teamTotalScore = in.readInt();
        teamRoundScore = in.readInt();
        teamID = in.readInt();
        players = in.readArrayList(null);
    }

    public static final Creator<TeamBean> CREATOR
            = new Creator<TeamBean>() {
        public TeamBean createFromParcel(Parcel in) {
            return new TeamBean(in);
        }

        public TeamBean[] newArray(int size) {
            return new TeamBean[size];
        }
    };
}
