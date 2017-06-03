package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.util.ArrayList;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class Gameplay {

    private ArrayList<Player> players;
    private Board board;
    private int period=1;
    private int turn=1;

    public Gameplay(ArrayList<Player> players) {
        this.players = players;
    }

    private ArrayList<Player> makeTurnOrder() {
        ArrayList<FamilyMember> familyMembersOrder = board.getBoard().getCouncilSpace().getPlayerOrder();
        ArrayList<Player> playersOrder = new ArrayList<Player>();
        for(FamilyMember f: familyMembersOrder){
            playersOrder.add(f.getPlayer());
        }
        return playersOrder;
    }

    private ArrayList<Player> makeMilitaryOrder() {
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

    public void scheduling() {
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
        getWinner();
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
        for(MarketSpace m: board.getMarketSpaces()){
            m.resetFamilyMember();
        }
        board.getCouncilSpace().resetFamilyMember();
        board.getProductionSpace().resetFamilyMember();
        board.getHarvestSpace().resetFamilyMember();
        //todo: in 1.2 2.2 e 3.2 rapporto al vaticano: scomunica

    }

    private void checkEndGame() {
        for (Player p : players) {
            //assegna punti territory cards
            switch (p.getCardOfPlayer().getTerritoryCards().size()) {
                case 3:
                    p.getResources().getVictoryPointsObj().add(1);
                case 4:
                    p.getResources().getVictoryPointsObj().add(4);
                case 5:
                    p.getResources().getVictoryPointsObj().add(10);
                case 6:
                    p.getResources().getVictoryPointsObj().add(20);
                default:
                    p.getResources().getVictoryPointsObj().add(0);
            }

            //assegna punti character cards
            switch (p.getCardOfPlayer().getCharacterCards().size()) {
                case 1:
                    p.getResources().getVictoryPointsObj().add(1);
                case 2:
                    p.getResources().getVictoryPointsObj().add(3);
                case 3:
                    p.getResources().getVictoryPointsObj().add(6);
                case 4:
                    p.getResources().getVictoryPointsObj().add(10);
                case 5:
                    p.getResources().getVictoryPointsObj().add(15);
                case 6:
                    p.getResources().getVictoryPointsObj().add(21);
                default:
                    p.getResources().getVictoryPointsObj().add(0);
            }

            //assegna punti venture cards
            for (VentureCard v : p.getCardOfPlayer().getVentureCards()) {
                for (int i = 0; i < v.getPermanentEffect().size(); i++) {
                    v.getPermanentEffect().get(i).activeEffect(p);
                }
            }
        }

        //assegno victory points in base all'ordine militare
        ArrayList<Player> order = makeMilitaryOrder();
        if (order.get(0).getResources().getMilitaryPoints() == order.get(1).getResources().getMilitaryPoints()) {
            order.get(0).getResources().getVictoryPointsObj().add(5);
            order.get(1).getResources().getVictoryPointsObj().add(5);
            order.get(2).getResources().getVictoryPointsObj().add(2);
        }
        else if (order.get(1).getResources().getMilitaryPoints() == order.get(2).getResources().getMilitaryPoints()) {
            order.get(0).getResources().getVictoryPointsObj().add(5);
            order.get(1).getResources().getVictoryPointsObj().add(2);
            order.get(2).getResources().getVictoryPointsObj().add(2);
        } else {
            order.get(0).getResources().getVictoryPointsObj().add(5);
            order.get(1).getResources().getVictoryPointsObj().add(2);
        }

        //assegna victorypoints in base alle risorse
        for(Player p : players){
            int sum= (p.getResources().getGold()+p.getResources().getStone()+p.getResources().getWood()+p.getResources().getServants());
            double number = sum/5;
            p.getResources().getVictoryPointsObj().add((int)number);
        }
    }

    private void getWinner(){
        ArrayList<Player> playersOrder = new ArrayList<Player>();
        for(int i=0; i<players.size(); i++){
            for(int j=0; j<players.size(); j++){
                if(players.get(i).getResources().getVictoryPoints()>=players.get(j).getResources().getVictoryPoints()){
                }
                else{
                    break;
                }
            }
            playersOrder.add(playersOrder.get(i));
        }
        System.out.println("THE WINNER IS PLAYER: "+playersOrder.get(0).getPlayerColor());
        System.out.println("Victory order: ");
        for (Player p: playersOrder){
            System.out.println(p.getPlayerColor());
            System.out.println("-----------------");
        }
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