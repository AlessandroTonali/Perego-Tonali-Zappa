package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;

import java.util.ArrayList;

/**
 * Created by jesss on 02/06/17.
 */
public class Creator {
    private Board board;
    private ArrayList<Player> players;

    public Creator(){
        board= board.getBoard();
        //todo: settare le carte nella tower
        players = new ArrayList<Player>();
        //todo: bonus tile
        players.add(new Player(PlayerColor.RED, null));
        players.add(new Player(PlayerColor.GREEN, null));
        players.add(new Player(PlayerColor.BLUE, null));
        players.add(new Player(PlayerColor.YELLOW, null));
        for(Player p: players){
            FamilyMember[] familyMembers = new FamilyMember[4];
            familyMembers[0]= new FamilyMember(p, FamilyColor.ORANGE, board.getDiceOValue());
            familyMembers[1]= new FamilyMember(p, FamilyColor.WHITE, board.getDiceWValue());
            familyMembers[2]= new FamilyMember(p, FamilyColor.BLACK, board.getDiceBValue());
            familyMembers[3]= new FamilyMember(p, FamilyColor.NEUTRAL, 0);
            p.setFamilyMembers(familyMembers);

        }
    }
}
