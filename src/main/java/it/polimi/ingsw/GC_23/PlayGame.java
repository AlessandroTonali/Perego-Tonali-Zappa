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
            for(Player p : players) {
                if(p.getUserHandler().isGuiInterface()) {
                    update();
                    sendTurnPlayer(players.get(0));
                }
            }

            while( i < 1 ){
                for (Player p : this.players) {
                    if(p.getUserHandler().isGuiInterface()) {
                        update();
                        sendTurnPlayer(p);
                    }
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
                       if(!p.getUserHandler().isGuiInterface()) {
                           p.getUserHandler().messageToUser("You receive the excommunication");
                       }
                    } else {
                        board.getExcommunicationSpaceFirstPeriod().chooseExcommunication(p);

                    }
                    break;
                case 2:
                    if (p.getResources().getFaithPoints() < 4) {
                        board.getExcommunicationSpaceSecondPeriod().getExcommunicationTile().takeExcommunication(p);
                       if(!p.getUserHandler().isGuiInterface()) {
                           p.getUserHandler().messageToUser("You receive the excommunication");
                       }
                    } else {
                        board.getExcommunicationSpaceSecondPeriod().chooseExcommunication(p);
                    }
                    break;
                case 3:
                    if (p.getResources().getFaithPoints() < 4) {
                        board.getExcommunicationSpaceThirdPeriod().getExcommunicationTile().takeExcommunication(p);
                        if(!p.getUserHandler().isGuiInterface()) {
                            p.getUserHandler().messageToUser("You receive the excommunication");
                        }
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
            if(!p.getUserHandler().isGuiInterface()) {
                p.getUserHandler().messageToUser(("----------------END----------------\n"));
                p.getUserHandler().messageToUser("THE WINNER IS PLAYER: " + playersOrder.get(0).getUserHandler().getCurrentUser() + "\n");
                p.getUserHandler().messageToUser("Victory order: \n");
            }
            for (Player pl : playersOrder) {
                if(!p.getUserHandler().isGuiInterface()) {
                    p.getUserHandler().messageToUser(pl.getUserHandler().getCurrentUser() + "\n");
                    p.getUserHandler().messageToUser("-----------------\n");
                }
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

    public void boardStringer(Player player) throws RemoteException{
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++) {
                player.getUserHandler().messageToUser("tower" + i + j);
                player.getUserHandler().messageToUser(String.valueOf(board.getTower(i).getSpaces()[j].getCard().getIdCard()));
                try {
                    player.getUserHandler().messageToUser("towerspace" + i + j);
                    player.getUserHandler().messageToUser(board.getTower(i).getSpaces()[j].getFamilyMember().getPlayer().getPlayerColor().toString() + board.getTower(i).getSpaces()[j].getFamilyMember().getFamilyColor().toString());
                } catch (NullPointerException e) {
                    player.getUserHandler().messageToUser("null");
                }
            }
        }
        for(int i = 0; i<4; i++){
            try {
                player.getUserHandler().messageToUser("market" + i);
                player.getUserHandler().messageToUser(board.getMarketSpaces()[i].getFamilyMember().getPlayer().getPlayerColor().toString() + board.getTower(i).getSpaces()[i].getFamilyMember().getFamilyColor().toString());
            }catch (NullPointerException e){
                player.getUserHandler().messageToUser("null");
            }catch (ArrayIndexOutOfBoundsException e){
                player.getUserHandler().messageToUser("not");
            }
        }
        for(int i = 0; i<16; i++){
            try{
                player.getUserHandler().messageToUser("harvest"+i);
                player.getUserHandler().messageToUser(board.getHarvestSpace().getFamilyMembersPresent().get(i).getPlayer().getPlayerColor().toString() + board.getHarvestSpace().getFamilyMembersPresent().get(i).getFamilyColor().toString());
            }catch (NullPointerException e){
                break;
            }
            catch (IndexOutOfBoundsException ex){
                player.getUserHandler().messageToUser("harvestend");
                break;
            }
        }
        for(int i = 0; i<16; i++){
            try{
                player.getUserHandler().messageToUser("production"+i);
                player.getUserHandler().messageToUser(board.getProductionSpace().getPlayerOrder().get(i).getPlayer().getPlayerColor().toString() + board.getProductionSpace().getPlayerOrder().get(i).getFamilyColor().toString());
            }catch (NullPointerException e){
                break;
            }catch (IndexOutOfBoundsException e){
                player.getUserHandler().messageToUser("productionend");
                break;
            }
        }
        for(int i = 0; i<4; i++){
            try {
                player.getUserHandler().messageToUser("council"+i);
                player.getUserHandler().messageToUser(board.getCouncilSpace().getPlayerOrder().get(i).getPlayerColor().toString());
            }catch (NullPointerException e){
                break;
            }catch (IndexOutOfBoundsException e){
                player.getUserHandler().messageToUser("councilend");
                break;
            }
        }
        player.getUserHandler().messageToUser(String.valueOf(board.getExcommunicationSpaceFirstPeriod().getExcommunicationTile().getIdTile()));
        player.getUserHandler().messageToUser(String.valueOf(board.getExcommunicationSpaceSecondPeriod().getExcommunicationTile().getIdTile()));
        player.getUserHandler().messageToUser(String.valueOf(board.getExcommunicationSpaceThirdPeriod().getExcommunicationTile().getIdTile()));
        player.getUserHandler().messageToUser("end");
    }

    public void sendBoard() throws RemoteException{
        for(Player p : players) {
                boardStringer(p);
        }
    }

    public void dataStringer(Player p) throws RemoteException{
        for(Player player: players) {
            p.getUserHandler().messageToUser(player.getPlayerColor().toString());
            p.getUserHandler().messageToUser(String.valueOf(player.getResources().getGold()));
            p.getUserHandler().messageToUser(String.valueOf(player.getResources().getStone()));
            p.getUserHandler().messageToUser(String.valueOf(player.getResources().getWood()));
            p.getUserHandler().messageToUser(String.valueOf((player.getResources().getServants())));
            p.getUserHandler().messageToUser(String.valueOf(player.getResources().getFaithPointsObj()));
            p.getUserHandler().messageToUser(String.valueOf(player.getResources().getMilitaryPoints()));
            p.getUserHandler().messageToUser(String.valueOf(player.getResources().getVictoryPoints()));
            p.getUserHandler().messageToUser("endResources");
            p.getUserHandler().messageToUser(String.valueOf(p.getFamilyMembers()[0].getValue()));
            p.getUserHandler().messageToUser(String.valueOf(p.getFamilyMembers()[1].getValue()));
            p.getUserHandler().messageToUser(String.valueOf(p.getFamilyMembers()[2].getValue()));
            p.getUserHandler().messageToUser(String.valueOf(p.getFamilyMembers()[3].getValue()));
        }

    }

    public void sendData() throws RemoteException{
        for(Player p : players){
                dataStringer(p);
        }
        for(Player p : players) {
            p.getUserHandler().messageToUser("dataended");
        }
    }

    public void cardsStringer(Player player) throws RemoteException{
        player.getUserHandler().messageToUser(player.getPlayerColor().toString());
            for (int i = 0; i < 6; i++) {
                try {
                    player.getUserHandler().messageToUser(String.valueOf(player.getCardOfPlayer().getTerritoryCards().get(i).getIdCard()));
                } catch (IndexOutOfBoundsException e) {
                    player.getUserHandler().messageToUser("null");
                }
            }
            for (int i = 0; i < 6; i++) {// invio character
                try {
                    player.getUserHandler().messageToUser(String.valueOf(player.getCardOfPlayer().getCharacterCards().get(i).getIdCard()));
                } catch (IndexOutOfBoundsException e) {
                    player.getUserHandler().messageToUser("null");
                }
            }
            for (int i = 0; i < 6; i++) {// invio building
                try {
                    player.getUserHandler().messageToUser(String.valueOf(player.getCardOfPlayer().getBuildingCards().get(i).getIdCard()));
                } catch (IndexOutOfBoundsException e) {
                    player.getUserHandler().messageToUser("null");
                }
            }
            for (int i = 0; i < 6; i++) { // invio venture
                try {
                    player.getUserHandler().messageToUser(String.valueOf(player.getCardOfPlayer().getVentureCards().get(i).getIdCard()));
                } catch (IndexOutOfBoundsException e) {
                    player.getUserHandler().messageToUser("null");
                }
            }
            for (int i = 0; i < 4; i++) { // invio leader
                try {
                    player.getUserHandler().messageToUser(String.valueOf(player.getLeaderCards().get(i).getIdCard()));
                } catch (IndexOutOfBoundsException e) {
                    player.getUserHandler().messageToUser("null");
                }
            }
            player.getUserHandler().messageToUser("end");
    }

    public void sendCards() throws RemoteException{
        for(Player p : players){
            cardsStringer(p);
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