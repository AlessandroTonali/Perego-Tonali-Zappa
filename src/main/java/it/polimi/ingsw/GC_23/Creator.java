package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Connection.UserHandler;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jesss on 02/06/17.
 */
public class Creator {

    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();
    private PlayGame playGame;

    /**
     * class for initialize the resources and card at the star of  the game
     * @param numberOfPlayers in the match
     */
    public Creator(int numberOfPlayers){
        board= new Board(numberOfPlayers);

    }

    public void startGame(int number, boolean isAdvanced) throws IOException {
        switch (number){
            case 2:
                Collections.shuffle(players);
                players.get(0).getResources().getGoldObj().add(5);
                players.get(1).getResources().getGoldObj().add(6);
                break;
            case 3:
                Collections.shuffle(players);
                players.get(0).getResources().getGoldObj().add(5);
                players.get(1).getResources().getGoldObj().add(6);
                players.get(2).getResources().getGoldObj().add(7);
                break;
            case 4:
                Collections.shuffle(players);
                players.get(0).getResources().getGoldObj().add(5);
                players.get(1).getResources().getGoldObj().add(6);
                players.get(2).getResources().getGoldObj().add(7);
                players.get(3).getResources().getGoldObj().add(8);
                break;
        }
        playGame = new PlayGame(players, board, isAdvanced);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player createPlayer(PlayerColor playerColor){
        Player player = new Player(playerColor, ParseJson.getParseJson().getBonusTile1());
        player.getBonusTile().setIdBonusTile(80);
        this.players.add(player);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(0,0,0,3,2,0,2));
        return player;
    }

    /**
     * method for create and inizialize the player
     * @param playerColor color of the player
     * @param userHandler handler associated at the player
     * @return
     */
    public Player createPlayer(PlayerColor playerColor, UserHandler userHandler){
        Player player = new Player(playerColor, ParseJson.getParseJson().getBonusTile1());
        this.players.add(player);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(0,0,0,3,2,0,2));
        player.setUserHandler(userHandler);
        ArrayList<LeaderCard> leaderCards = ParseJson.getParseJson().getLeaderCardArrayList();
        int startIndex = (players.size()-1)*4;
        ArrayList<LeaderCard> playerLeaderCard = new ArrayList<>();
        int finalIndex = (players.size())*4;
        for (int j = startIndex; j < finalIndex; j++) {
            playerLeaderCard.add(leaderCards.get(j));
        }
        player.setLeaderCards(playerLeaderCard);

        return player;
    }

    public Board getBoard() {
        return board;
    }

    public PlayGame getPlayGame(){
        return playGame;
    }

}
