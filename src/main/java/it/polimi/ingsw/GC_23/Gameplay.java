package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.awt.image.AreaAveragingScaleFilter;
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
        this.board = new Board(4);
        scheduling();


    }

    private boolean isPresent(Player player){
        for(Player p : this.players) {
            if(p == player){
                return true;
            }
        }
        return false;
    }

    private void makeTurnOrder() {
        ArrayList<Player> pastOrder = this.players;
        this.players = board.getCouncilSpace().getPlayerOrder();
        for(Player p : pastOrder){
            if(!isPresent(p)){
                players.add(p);
            }
        }


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
        int i = 0;
        resetFamilyMembers();
        while(true) {
            System.out.println(period + " period");
            while( i < 1 ){
                for (Player p : this.players) {
                    System.out.println("Period: " + this.period + " Turn: " + this.turn);
                    System.out.println(p.getPlayerColor().toString() + ": it's your turn!");
                    p.chooseMove(this.board);
                }
                i++;
            }
            i = 0;
            System.out.println("fine turno");
                if (turn == 1) {
                    turn++;
                    makeTurnOrder();
                    System.out.println(period + "period" );
                }
                else if(period == 3){
                    break;
                }
                else if (turn == 2) {
                    System.out.println(turn + "turno");
                    period++;
                    turn = 1;
                    checkEndPeriod();
                }
            System.out.println("periodo " + period);
            System.out.println("turno " + turn);
            }

        System.out.println("END");
        checkEndGame();
        getWinner();
    }

    private void checkEndPeriod() {
        board.resetCardTowers();
       //board.setCards();//
        makeTurnOrder();
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
                    break;
                case 4:
                    p.getResources().getVictoryPointsObj().add(4);
                    break;
                case 5:
                    p.getResources().getVictoryPointsObj().add(10);
                    break;
                case 6:
                    p.getResources().getVictoryPointsObj().add(20);
                    break;
                default:
                    p.getResources().getVictoryPointsObj().add(0);
                    break;
            }

            //assegna punti character cards
            switch (p.getCardOfPlayer().getCharacterCards().size()) {
                case 1:
                    p.getResources().getVictoryPointsObj().add(1);
                    break;
                case 2:
                    p.getResources().getVictoryPointsObj().add(3);
                    break;
                case 3:
                    p.getResources().getVictoryPointsObj().add(6);
                    break;
                case 4:
                    p.getResources().getVictoryPointsObj().add(10);
                    break;
                case 5:
                    p.getResources().getVictoryPointsObj().add(15);
                case 6:
                    p.getResources().getVictoryPointsObj().add(21);
                    break;
                default:
                    p.getResources().getVictoryPointsObj().add(0);
                    break;
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
                        break;
                    case WHITE:
                        f.setValue(board.getDiceWValue());
                        break;
                    case BLACK:
                        f.setValue(board.getDiceBValue());
                        break;
                    case NEUTRAL:
                        f.setValue(0);
                        break;

                }
            }
        }
    }

}