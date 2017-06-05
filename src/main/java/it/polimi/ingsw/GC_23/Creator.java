package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jesss on 02/06/17.
 */
public class Creator {
    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Gameplay gameplay;
    private int numberOfPlayers;

    //todo:disporre tessere scomunica
    public Creator(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        board= new Board();
        //todo: settare le carte nella tower
    }

    public void startGame(){
        Collections.shuffle(players);
        players.get(0).getResources().getGoldObj().add(5);
        players.get(1).getResources().getGoldObj().add(6);
        players.get(2).getResources().getGoldObj().add(7);
        players.get(3).getResources().getGoldObj().add(8);
        gameplay= new Gameplay(players);
        //gameplay.scheduling();
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player createPlayer(String color){
        //todo: assegno bonus tile
        //todo: 4 player al massimo
        PlayerColor playerColor = PlayerColor.valueOf(color);
        Player player = new Player(playerColor, null);
        this.players.add(player);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0]= new FamilyMember(player, FamilyColor.ORANGE, board.getDiceOValue());
        familyMembers[1]= new FamilyMember(player, FamilyColor.WHITE, board.getDiceWValue());
        familyMembers[2]= new FamilyMember(player, FamilyColor.BLACK, board.getDiceBValue());
        familyMembers[3]= new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(0,0,0,3,2,0,2));
        return player;
    }

    public Board getBoard() {
        return board;
    }
}
