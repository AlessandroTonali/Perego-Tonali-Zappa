package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Effects.EndGameEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class PlayGame {

    private ArrayList<Player> players;
    private Board board;
    private int period=1;
    private int turn=1;

    public PlayGame(ArrayList<Player> players, Board board) throws IOException {
        this.players = players;
        this.board = board;
        scheduling();
    }

    public PlayGame(ArrayList<Player> players) throws IOException {
        this.players = players;
        this.board = new Board(4);
        scheduling();
    }

    public void scheduling() throws IOException {
        int i = 0;
        resetFamilyMembers();
        while(true) {
            System.out.println(period + " period");
            while( i < 1 ){
                for (Player p : this.players) {
                    p.getUserHandler().messageToUser("");
                    p.getUserHandler().messageToUser(("Period: " + this.period + " Turn: " + this.turn + "\n"));
                    p.getUserHandler().messageToUser(p.getUserHandler().getCurrentUser() + ": it's your turn!\n");
                    p.chooseMove(this.board,0);
                }
                i++;
            }
            i = 0;
            if (turn == 1) {
                turn++;
                makeTurnOrder();
                board.setCard();
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
            }
            else if(period == 3 ){
                break;
            }
            else if (turn == 2) {
                period++;
                turn = 1;
                checkEndPeriod();
            }
            System.out.println("Periodo " + period);
            System.out.println("Turno " + turn);
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
        return playersOrder;
    }

    private void checkEndPeriod() throws IOException {
        board.resetCardTowers();
        board.setCard();
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

        for (Player p : players){
            switch (period) {
                case 1:
                    if (p.getResources().getFaithPoints() < 3) {
                        board.getExcommunicationSpaceFirstPeriod().getExcommunicationTile().takeExcommunication(p);
                        p.getUserHandler().messageToUser("You receive the excommunication");
                    } else {
                        //TODO SCELTA
                        board.getExcommunicationSpaceFirstPeriod().chooseExcommunication(p);

                    }
                    break;
                case 2:
                    if (p.getResources().getFaithPoints() < 4) {
                        board.getExcommunicationSpaceFirstPeriod().getExcommunicationTile().takeExcommunication(p);
                    } else {
                        //TODO SCELTA
                    }
                    break;
                case 3:
                    if (p.getResources().getFaithPoints() < 4) {
                        board.getExcommunicationSpaceFirstPeriod().getExcommunicationTile().takeExcommunication(p);
                    } else {
                        //TODO SCELTA
                    }
                    break;

            }
        }

    }

    private void checkEndGame() throws IOException {
        for (Player p : players) {
            //assegna punti territory cards
            if (!p.isNotScoreTerrytory()) {
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
            }

            //assegna punti character cards
            if (!p.isNotScoreCharacter()) {
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
            }

            //assegna punti venture cards
            if (!p.isNotScoreVenture()) {
                for (VentureCard v : p.getCardOfPlayer().getVentureCards()) {
                    for (int i = 0; i < v.getPermanentEffect().size(); i++) {
                        v.getPermanentEffect().get(i).activeEffect(p);
                    }
                }
            }

        }

        //assegno victory points in base all'ordine militare
        ArrayList<Player> order = makeMilitaryOrder();
        if(players.size() == 2) {
                if(order.get(0).getResources().getMilitaryPoints() == order.get(1).getResources().getMilitaryPoints()) {
                    order.get(0).getResources().getVictoryPointsObj().add(5);
                    order.get(1).getResources().getVictoryPointsObj().add(5);
                }
                else {
                    order.get(0).getResources().getVictoryPointsObj().add(5);
                    order.get(1).getResources().getVictoryPointsObj().add(2);
                }



        } else {
            if (order.get(0).getResources().getMilitaryPoints() == order.get(1).getResources().getMilitaryPoints()) {
                order.get(0).getResources().getVictoryPointsObj().add(5);
                order.get(1).getResources().getVictoryPointsObj().add(5);
                order.get(2).getResources().getVictoryPointsObj().add(2);
            } else if (order.get(1).getResources().getMilitaryPoints() == order.get(2).getResources().getMilitaryPoints()) {
                order.get(0).getResources().getVictoryPointsObj().add(5);
                order.get(1).getResources().getVictoryPointsObj().add(2);
                order.get(2).getResources().getVictoryPointsObj().add(2);
            } else {
                order.get(0).getResources().getVictoryPointsObj().add(5);
                order.get(1).getResources().getVictoryPointsObj().add(2);
            }

            //assegna victorypoints in base alle risorse
            for (Player p : players) {
                int sum = (p.getResources().getGold() + p.getResources().getStone() + p.getResources().getWood() + p.getResources().getServants());
                double number = sum / 5;
                p.getResources().getVictoryPointsObj().add((int) number);
            }
        }

        for (Player p : players) {
            for (int i = 0; i < p.getPermanentEffects().size(); i++) {
                PermanentEffect effect = p.getPermanentEffects().get(i);
                if (effect instanceof EndGameEffect) {
                    effect.activeEffect(p);
                }
            }
        }

    }

    private void getWinner() throws RemoteException {
        ArrayList<Player> playersOrder = players;
        for (int i = 0; i < playersOrder.size(); i++) {
            for (int j = 0; j < playersOrder.size() - 1; j++) {
                if (playersOrder.get(j).getResources().getVictoryPoints() < playersOrder.get(j + 1)
                        .getResources().getVictoryPoints()) {
                    Player tmp = playersOrder.get(j);
                    playersOrder.set(j, playersOrder.get(j + 1));
                    playersOrder.set(j + 1, tmp);
                }
            }
        }
        for (Player p : this.players) {
            p.getUserHandler().messageToUser(("----------------END----------------\n"));
            p.getUserHandler().messageToUser("THE WINNER IS PLAYER: " + playersOrder.get(0).getUserHandler().getCurrentUser() + "\n");
            p.getUserHandler().messageToUser("Victory order: \n");
            for (Player pl : playersOrder) {
                p.getUserHandler().messageToUser(pl.getUserHandler().getCurrentUser()+"\n");
                p.getUserHandler().messageToUser("-----------------\n");
            }
        }
    }

    private void resetFamilyMembers(){


        for(Player p: players){
            FamilyMember[] familyMembers = new FamilyMember[4];

            familyMembers[0] = new FamilyMember(p, FamilyColor.ORANGE, 0);
            familyMembers[1] = new FamilyMember(p, FamilyColor.WHITE, 0);
            familyMembers[2] = new FamilyMember(p, FamilyColor.BLACK, 0);
            familyMembers[3] = new FamilyMember(p, FamilyColor.NEUTRAL, 0);
            for(FamilyMember f: familyMembers){
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
            p.setFamilyMembers(familyMembers);
        }
    }
}