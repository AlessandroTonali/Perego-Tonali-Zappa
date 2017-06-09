package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Connection.UserHandler;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
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

    public Creator(){

    }

    public Creator(int numberOfPlayers){
        board= new Board(numberOfPlayers);
    }

    public void startGame(){
        Collections.shuffle(players);
        players.get(0).getResources().getGoldObj().add(5);
        players.get(1).getResources().getGoldObj().add(6);
        players.get(2).getResources().getGoldObj().add(7);
        players.get(3).getResources().getGoldObj().add(8);
        gameplay= new Gameplay(players, board);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player createPlayer(PlayerColor playerColor){
        Player player = new Player(playerColor, ParseJson.getParseJson().getBonusTile1());
        this.players.add(player);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        return player;
    }

    public Player createPlayer(PlayerColor playerColor, UserHandler userHandler){
        Player player = new Player(playerColor, ParseJson.getParseJson().getBonusTile1());
        this.players.add(player);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        player.setUserHandler(userHandler);
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public Gameplay getGameplay(){
        return gameplay;
    }
}
