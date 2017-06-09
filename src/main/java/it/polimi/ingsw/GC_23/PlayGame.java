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
public class PlayGame {

    private ArrayList<Player> players;
    private Board board;
    private int period=1;
    private int turn=1;

    public PlayGame(ArrayList<Player> players, Board board) {
        this.players = players;
        this.board = board;
        scheduling();
    }

    public PlayGame(ArrayList<Player> players) {
        this.players = players;
        this.board = new Board(4);
        scheduling();
    }

    public void scheduling() {
        int i = 0;
        resetFamilyMembers();
        while(true) {
            System.out.println(period + "period");
            while( i < 1 ){
                for (Player p : this.players) {
                    System.out.println("Period: " + this.period + "Turn: " + this.turn);
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
        ArrayList<Player> playersOrder = players;
        for(int i=0; i<playersOrder.size();i++){
            for(int j = 0; j<playersOrder.size() - 1;j++){
                if(playersOrder.get(j).getResources().getMilitaryPoints() < playersOrder.get(j+1)
                        .getResources().getMilitaryPoints()) {
                    Player tmp = playersOrder.get(j);
                    playersOrder.set(j, playersOrder.get(j+1));
                    playersOrder.set(j+1,tmp);
                }
            }
        }
        System.out.println("ce l'ho fatta");
        return playersOrder;
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

    private void getWinner() {
        ArrayList<Player> playersOrder = players;
        for (int i = 0; i < playersOrder.size(); i++) {
            for (int j = 0; j < playersOrder.size() - 1; j++) {
                if (playersOrder.get(j).getResources().getVictoryPoints() < playersOrder.get(j + 1)
                        .getResources().getVictoryPoints()) {
                    Player tmp = playersOrder.get(j);
                    playersOrder.set(j, playersOrder.get(j + 1));
                    playersOrder.set(j + 1, tmp);
                    System.out.println("sono nel for");
                }

            }
            System.out.println("THE WINNER IS PLAYER: " + playersOrder.get(0).getPlayerColor());
            System.out.println("Victory order: ");
            for (Player p : playersOrder) {
                System.out.println(p.getPlayerColor());
                System.out.println("-----------------");
            }
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