package com.example.odonovac.onehundredandten;

import android.app.Application;

import com.example.odonovac.onehundredandten.beans.PlayerBean;
import com.example.odonovac.onehundredandten.beans.TeamBean;

import java.util.ArrayList;

/**
 *
 * Created by Cathy-Ann on 09/11/2014.
 */
public class MyApplication extends Application {

    private ArrayList<PlayerBean> players;

    private ArrayList<TeamBean> teams;

    private static final int SINGLE = 0;
    private static final int TEAM = 1;

    private int gameMode;


    @Override
    public void onCreate(){
        super.onCreate();
        players = new ArrayList<PlayerBean>();
        teams = new ArrayList<TeamBean>();
    }

    public void setPlayers(ArrayList<PlayerBean> players){
        this.players = players;
    }

    public ArrayList<PlayerBean> getPlayers(){
        return this.players;
    }

    public void setTeams(ArrayList<TeamBean> teams){
        this.teams = teams;
    }

    public ArrayList<TeamBean> getTeams(){
        return this.teams;
    }

    public void setGameMode(int mode){
        this.gameMode = mode;
    }

    public int getGameMode(){
        return this.gameMode;
    }

    public void createTeams(){
        for(PlayerBean player : players){
            int index = player.getTeamID();

            if(null == teams.get(index)){
                TeamBean team = new TeamBean();
                team.addPlayer(player);
            }
            else
            {
                TeamBean team = teams.get(index);
                team.addPlayer(player);
            }
        }
    }
}
