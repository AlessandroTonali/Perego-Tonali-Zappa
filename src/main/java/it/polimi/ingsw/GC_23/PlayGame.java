package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.LeaderCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Connection.UserHandler;
import it.polimi.ingsw.GC_23.Effects.EndGameEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

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
    private boolean isAdvanced;

    public PlayGame(ArrayList<Player> players, Board board, boolean isAdvanced) throws IOException {
        this.players = players;
        this.board = board;
        this.isAdvanced = isAdvanced;
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
            sendBoard();
            sendData();
            sendCards();
            sendTurnPlayer(players.get(0));

            while( i < 1 ){
                for (Player p : this.players) {
                    sendTurnPlayer(p);
                    if(!p.getUserHandler().isGuiInterface()) {
                        p.getUserHandler().messageToUser("");
                        p.getUserHandler().messageToUser(("Period: " + this.period + " Turn: " + this.turn + "\n"));
                        p.getUserHandler().messageToUser(p.getUserHandler().getCurrentUser() + ": it's your turn!\n");
                    }
                    p.chooseMove(this.board, this.isAdvanced);

                    for (LeaderCard leaderCard : p.getLeaderCards()){
                        leaderCard.setDiscardedInThisTurn(false);
                    }
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
                for (Player p : players) {
                   try {
                       for (LeaderCard leaderCard : p.getLeaderCards()) {
                           leaderCard.setActivatedInThisRound(false);
                       }
                   }catch (NullPointerException e){

                   }
                }
                board.getCouncilSpace().resetFamilyMember();
                board.getProductionSpace().resetFamilyMember();
                board.getHarvestSpace().resetFamilyMember();
            }
            else if(period == 3 ){
                break;
            }
            else if (turn == 2) {
                checkEndPeriod();
                period++;
                turn = 1;
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
        for (Player p : players) {
            try {
                for (LeaderCard leaderCard : p.getLeaderCards()) {
                    leaderCard.setActivatedInThisRound(false);
                }
            }catch (NullPointerException e){

            }
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
                        board.getExcommunicationSpaceFirstPeriod().chooseExcommunication(p);

                    }
                    break;
                case 2:
                    if (p.getResources().getFaithPoints() < 4) {
                        board.getExcommunicationSpaceSecondPeriod().getExcommunicationTile().takeExcommunication(p);
                        p.getUserHandler().messageToUser("You receive the excommunication");
                    } else {
                        board.getExcommunicationSpaceSecondPeriod().chooseExcommunication(p);
                    }
                    break;
                case 3:
                    if (p.getResources().getFaithPoints() < 4) {
                        board.getExcommunicationSpaceThirdPeriod().getExcommunicationTile().takeExcommunication(p);
                        p.getUserHandler().messageToUser("You receive the excommunication");
                    } else {
                        board.getExcommunicationSpaceThirdPeriod().chooseExcommunication(p);
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

    private void resetFamilyMembers() throws RemoteException {
        for(Player p: players){
            FamilyMember[] familyMembers = new FamilyMember[4];

            familyMembers[0] = new FamilyMember(p, FamilyColor.ORANGE, 0);
            familyMembers[1] = new FamilyMember(p, FamilyColor.WHITE, 0);
            familyMembers[2] = new FamilyMember(p, FamilyColor.BLACK, 0);
            familyMembers[3] = new FamilyMember(p, FamilyColor.NEUTRAL, 0);
            for(FamilyMember f: familyMembers){
                switch(f.getFamilyColor()){
                    case ORANGE:
                        f.setValue(f.checkPermanentEffect(board.getDiceOValue()));
                        break;
                    case WHITE:
                        f.setValue(f.checkPermanentEffect(board.getDiceWValue()));
                        break;
                    case BLACK:
                        f.setValue(f.checkPermanentEffect(board.getDiceBValue()));
                        break;
                    case NEUTRAL:
                        f.setValue(f.checkPermanentEffect(0));
                        break;

                }
            }
            p.setFamilyMembers(familyMembers);
        }
    }

    public void sendBoard() throws RemoteException{
        String boardStringer = this.board.boardStringer();
        for(Player p : players) {
            p.getUserHandler().messageToUser(boardStringer);
        }
    }

    public String dataStringer() throws RemoteException{
        StringBuilder dataString = new StringBuilder();
        for(Player p : players) {
            dataString.append(p.getPlayerColor().toString() + "\n");
            dataString.append(p.getResources().getGold() + "\n");
            dataString.append(p.getResources().getStone() + "\n");
            dataString.append(p.getResources().getWood() + "\n");
            dataString.append(p.getResources().getServants() + "\n");
            dataString.append(p.getResources().getFaithPointsObj() + "\n");
            dataString.append(p.getResources().getMilitaryPoints() + "\n");
            dataString.append(p.getResources().getVictoryPoints() + "\n");
            dataString.append("endResources\n");
            dataString.append(p.getFamilyMembers()[0].getValue() + "\n");
            dataString.append(p.getFamilyMembers()[1].getValue() + "\n");
            dataString.append(p.getFamilyMembers()[2].getValue() + "\n");
            dataString.append(p.getFamilyMembers()[3].getValue() + "\n");
        }
        dataString.append("dataended\n");

        return String.valueOf(dataString);
    }

    public void sendData() throws RemoteException{
        String dataStringer = dataStringer();
        for(Player p : players){
            p.getUserHandler().messageToUser(dataStringer);
        }
    }

    public String cardsStringer() throws RemoteException{
        StringBuilder cardsString = new StringBuilder();
        for(Player p : players) {
            cardsString.append(p.getPlayerColor().toString()+"\n");
            for (int i = 0; i < 6; i++) {//invio territory
                try {
                    cardsString.append(p.getCardOfPlayer().getTerritoryCards().get(i).getIdCard() + "\n");
                } catch (IndexOutOfBoundsException e) {
                    cardsString.append("null\n");
                }
            }
            for (int i = 0; i < 6; i++) {// invio character
                try {
                    cardsString.append(p.getCardOfPlayer().getCharacterCards().get(i).getIdCard() + "\n");
                } catch (IndexOutOfBoundsException e) {
                    cardsString.append("null\n");
                }
            }
            for (int i = 0; i < 6; i++) {// invio building
                try {
                    cardsString.append(p.getCardOfPlayer().getBuildingCards().get(i).getIdCard() + "\n");
                } catch (IndexOutOfBoundsException e) {
                    cardsString.append("null\n");
                }
            }
            for (int i = 0; i < 6; i++) { // invio venture
                try {
                    cardsString.append(p.getCardOfPlayer().getVentureCards().get(i).getIdCard() + "\n");
                } catch (IndexOutOfBoundsException e) {
                    cardsString.append("null\n");
                }
            }
            for (int i = 0; i < 4; i++) { // invio leader
                try {
                    cardsString.append(p.getLeaderCards().get(i).getIdCard() + "\n");
                } catch (IndexOutOfBoundsException e) {
                    cardsString.append("null\n");
                }
            }
        }
        cardsString.append("end\n");
        return String.valueOf(cardsString);
    }

    public void sendCards() throws RemoteException{
        String cardsString = cardsStringer();
        for(Player p : players){
            p.getUserHandler().messageToUser(cardsString);
        }
    }

    public void sendTurnPlayer(Player p) throws RemoteException{
        for (Player pl : players){
            pl.getUserHandler().messageToUser(p.getPlayerColor().toString());
        }
    }

    public void update() throws RemoteException{
        sendBoard();
        sendData();
        sendCards();
    }
}