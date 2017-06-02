package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.util.ArrayList;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class Gameplay {

    private int playerNumber;
    private ArrayList<Player> players;
    private Board board;
    private int period=1;
    private int turn=1;

    public Gameplay(int playerNumber, ArrayList<Player> players) {
        this.playerNumber = playerNumber;
        this.players = players;
    }

    public ArrayList<Player> makeTurnOrder() {
        ArrayList<FamilyMember> familyMembersOrder = board.getBoard().getCouncilSpace().getPlayerOrder();
        ArrayList<Player> playersOrder = new ArrayList<Player>();
        for(FamilyMember f: familyMembersOrder){
            playersOrder.add(f.getPlayer());
        }
        return playersOrder;
    }

    public ArrayList<Player> makeMilitaryOrder() {
        ArrayList<Player> playersOrder = new ArrayList<Player>();
        for(int i=0; i<players.size(); i++){
            for(int j=0; j<players.size(); j++){
                if(players.get(i).getResources().getMilitaryPoints()>=players.get(j).getResources().getMilitaryPoints()){
                }
                else{
                    break;
                }
            }
            playersOrder.add(playersOrder.get(i));
        }
        return playersOrder;
    }

    private void scheduling() {
        for(Player p: players){
            System.out.println("Period: "+this.period+ "Turn: "+this.turn);
            System.out.println(p.getPlayerColor().toString()+": it's your turn!");
            p.chooseMove();
            if(turn==1){
                turn++;
            }
            if(turn==2){
                period++;
                turn=1;
                checkEndPeriod();
            }
            if((period==3)&&(turn==2)&&(players.get(players.size()-1)==p)){
                break;
            }
        }
        System.out.println("END");
        checkEndGame();
    }

    private void checkEndPeriod() {
        board.resetCardTowers();
        this.players = makeTurnOrder();
        board.setDices();
        resetFamilyMembers();

        for(Tower t: board.getTowers()){
            for(TowerSpace ts: t.getSpaces()){
                ts.resetFamilyMember();
            }
        }

        //tolgo i familiari da tutti gli spazi
        //todo: in 1.2 2.2 e 3.2 rapporto al vaticano: scomunica

    }

    private void checkEndGame(){
        //assegno victory points
    }

    private void resetFamilyMembers(){
        for(Player p: players){
            for(FamilyMember f: p.getFamilyMembers()){
                switch(f.getFamilyColor()){
                    case ORANGE:
                        f.setValue(board.getDiceOValue());
                    case WHITE:
                        f.setValue(board.getDiceWValue());
                    case BLACK:
                        f.setValue(board.getDiceBValue());
                    case NEUTRAL:
                        f.setValue(0);
                }
            }
        }
    }

}