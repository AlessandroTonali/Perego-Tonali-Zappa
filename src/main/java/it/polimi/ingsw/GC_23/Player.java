package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Connection.UserHandler;
import it.polimi.ingsw.GC_23.Controller.*;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Player implements Serializable {
    private transient Board view;
    private PlayerColor playerColor;
    private transient ResourcesSet resources;
    private transient CardOfPlayer cardOfPlayer;
    private transient BonusTile bonusTile;
    private transient FamilyMember[] familyMembers;
    private transient UserHandler userHandler;
    private ArrayList<PermanentEffect> permanentEffects;
    private ArrayList<LeaderCard> leaderCards;

    private boolean notScoreTerrytory = false;
    private boolean notScoreVenture = false;
    private boolean notScoreCharacter = false;
    private boolean notPlayInMarket = false;
    private boolean doubleServantToIncrease = false;
    private boolean notCheckMilitaryOnTerritory = false;
    private boolean pointOnVatican = false;
    private boolean notSpendOnOccupiedTower = false;
    private boolean timeIsOver = false;
    private boolean typed = false;
    private int typedInt;

    private transient final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Player(PlayerColor playerColor, BonusTile bonusTile) {
        this.playerColor = playerColor;
        this.bonusTile = bonusTile;
        this.cardOfPlayer = new CardOfPlayer();
        permanentEffects = new ArrayList<>();
        //permanent effect ancora non lo dobbiamo fare
    }

    public void setTimeIsOver(boolean timeIsOver) {
        this.timeIsOver = timeIsOver;
    }

    public void setTyped(boolean typed) {
        this.typed = typed;
    }

    public void setTypedInt(int typedInt) {
        this.typedInt = typedInt;
    }

    public void setBonusTile(BonusTile bonusTile) {
        this.bonusTile = bonusTile;
    }

    public void setResources(ResourcesSet resources) {
        this.resources = resources;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public ResourcesSet getResources() {
        return resources;
    }

    public CardOfPlayer getCardOfPlayer() {
        return cardOfPlayer;
    }

    public BonusTile getBonusTile() {
        return bonusTile;
    }

    public Board getView() {
        return view;
    }

    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }

    public void setCardOfPlayer(CardOfPlayer cardOfPlayer) {
        this.cardOfPlayer = cardOfPlayer;
    }

    public void setFamilyMembers(FamilyMember[] familyMembers) {
        this.familyMembers = familyMembers;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public boolean isNotScoreTerrytory() {
        return notScoreTerrytory;
    }

    public void setNotScoreTerrytory(boolean notScoreTerrytory) {
        this.notScoreTerrytory = notScoreTerrytory;
    }

    public boolean isNotScoreVenture() {
        return notScoreVenture;
    }

    public void setNotScoreVenture(boolean notScoreVenture) {
        this.notScoreVenture = notScoreVenture;
    }

    public boolean isNotScoreCharacter() {
        return notScoreCharacter;
    }

    public void setNotScoreCharacter(boolean notScoreCharacter) {
        this.notScoreCharacter = notScoreCharacter;
    }

    public boolean isNotPlayInMarket() {
        return notPlayInMarket;
    }

    public void setNotPlayInMarket(boolean notPlayInMarket) {
        this.notPlayInMarket = notPlayInMarket;
    }

    public boolean isDoubleServantToIncrease() {
        return doubleServantToIncrease;
    }

    public void setDoubleServantToIncrease(boolean doubleServantToIncrease) {
        this.doubleServantToIncrease = doubleServantToIncrease;
    }

    public boolean isNotCheckMilitaryOnTerritory() {
        return notCheckMilitaryOnTerritory;
    }

    public void setNotCheckMilitaryOnTerritory(boolean notCheckMilitaryOnTerritory) {
        this.notCheckMilitaryOnTerritory = notCheckMilitaryOnTerritory;
    }

    public boolean isPointOnVatican() {
        return pointOnVatican;
    }

    public void setPointOnVatican(boolean pointOnVatican) {
        this.pointOnVatican = pointOnVatican;
    }

    public boolean isNotSpendOnOccupiedTower() {
        return notSpendOnOccupiedTower;
    }

    public void setNotSpendOnOccupiedTower(boolean notSpendOnOccupiedTower) {
        this.notSpendOnOccupiedTower = notSpendOnOccupiedTower;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void chooseMove(Board board, boolean isAdvanced) throws IOException {
        this.view = board;
        if(!getUserHandler().isGuiInterface()) {
            getUserHandler().messageToUser("read");
            if (isAdvanced) {
                getUserHandler().messageToUser("press 0 for placing a familiar in council\n" +
                        "press 1 for getting the harvest\n" +
                        "press 2 for getting production\n" +
                        "press 3 for increasing your familiar value\n" +
                        "press 4 for for going in the market\n" +
                        "press 5 for going in the territory tower\n" +
                        "press 6 for going in the character tower\n" +
                        "press 7 for going in the building tower\n" +
                        "press 8 for going in the venture tower\n" +
                        "press 9 for watching the board\n" +
                        "press 10 for watching your resources\n" +
                        "press 11 to skip\n" +
                        "press 12 to active leader card\n" +
                        "press 13 to discard a leader card");
            } else {
                getUserHandler().messageToUser("press 0 for placing a familiar in council\n" +
                        "press 1 for getting the harvest\n" +
                        "press 2 for getting production\n" +
                        "press 3 for increasing your familiar value\n" +
                        "press 4 for for going in the market\n" +
                        "press 5 for going in the territory tower\n" +
                        "press 6 for going in the character tower\n" +
                        "press 7 for going in the building tower\n" +
                        "press 8 for going in the venture tower\n" +
                        "press 9 for watching the board\n" +
                        "press 10 for watching your resources\n" +
                        "press 11 to skip\n");
            }
        }
        int i = -1;
        PlayerTimeOut playerTimeOut = new PlayerTimeOut(this);
        StringTyper stringTyper = new StringTyper(this);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(playerTimeOut);
        executorService.submit(stringTyper);
        while (!typed && !timeIsOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
            }
        }
        if(timeIsOver){
            if(!getUserHandler().isGuiInterface()) {
                getUserHandler().messageToUser("read");
            }
            timeIsOver = false;
            return;
        }
        if(typed){
            typed = false;
            i = typedInt;
        }
        try {
            switch (i) {
                case -1:
                    chooseMove(view, isAdvanced);
                    playerTimeOut.setNeeded(false);
                    return;
                case 0:
                    new CouncilController(board.getCouncilSpace(), chooseFamilyMember(0));
                    break;
                case 1:
                    new HarvestController(chooseFamilyMember(0), board.getHarvestSpace());
                    break;
                case 2:
                    new ProductionController(chooseFamilyMember(0), new ProductionSpace());
                    break;
                case 3:
                    getUserHandler().messageToUser("read");
                    getUserHandler().messageToUser("How much?");
                    int j = -1;
                    StringTyper stringTyper1 = new StringTyper(this);
                    executorService.submit(stringTyper1);
                    while (!typed && !timeIsOver) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            logger.setLevel(Level.SEVERE);
                            logger.severe(String.valueOf(e));
                        }
                    }
                    if (timeIsOver) {
                        timeIsOver = false;
                        getUserHandler().messageToUser("read");
                        return;
                    }
                    if (typed) {
                        typed = false;
                        j = typedInt;
                    }
                    new IncreaseFamilyValue(j, chooseFamilyMember(0));
                    playerTimeOut.setNeeded(false);
                    chooseMove(this.view, isAdvanced);
                    return;
                case 4:
                    new MarketController(chooseFamilyMember(0), board.getMarketSpaces());
                    break;
                case 5:
                    new TerritoryController(chooseFamilyMember(0), board.getTower(0));
                    break;
                case 6:
                    new OtherCardsController(chooseFamilyMember(0), board.getTower(1));
                    break;
                case 7:
                    new OtherCardsController(chooseFamilyMember(0), board.getTower(2));
                    break;
                case 8:
                    new OtherCardsController(chooseFamilyMember(0), board.getTower(3));
                    break;
                case 9:
                    getUserHandler().messageToUser(view.toString());
                    playerTimeOut.setNeeded(false);
                    chooseMove(view, isAdvanced);

                    return;
                case 10:
                    getUserHandler().messageToUser(this.resources.toString());
                    getUserHandler().messageToUser("");
                    playerTimeOut.setNeeded(false);
                    chooseMove(view, isAdvanced);

                    return;
                case 11:
                    break;
                case 12:

                    if (isAdvanced) {
                        new ActiveLeaderCard(chooseLeaderCard(), this, playerTimeOut);
                        break;
                    }
                case 13:
                    if(isAdvanced){
                    new DiscardLeaderCard(chooseLeaderCard(), this, playerTimeOut);
                    break;
                }
                default:
                    getUserHandler().messageToUser("Wrong number selected, try again");
                    chooseMove(view, isAdvanced);
                    playerTimeOut.setNeeded(false);
                    return;

            }
        }catch (NullPointerException e){
            return;
        } catch (IllegalArgumentException e){
            chooseMove(view, isAdvanced);
        }
        playerTimeOut.setNeeded(false);
        getUserHandler().messageToUser("Wait for your turn\n");
        getUserHandler().messageToUser("wait");
        return;
    }


    public boolean isEquals(Player player) {
        if (this.playerColor == player.getPlayerColor()) {
            return true;
        } else {
            return false;
        }

    }

    public FamilyMember chooseFamilyMember(int value) throws IOException {
        int j = 0;
        for(FamilyMember f: familyMembers) {
            if( f == null){
                continue;
            }
            if(!getUserHandler().isGuiInterface()) {
                getUserHandler().messageToUser("Press " + j + " for choosing: " + f.toString());
            }
            if(getUserHandler().isGuiInterface()){
                userHandler.messageToUser(f.getFamilyColor().toString());
            }
            j++;
        }
        if(userHandler.isGuiInterface()){
            userHandler.messageToUser("end");
        }
        if(!getUserHandler().isGuiInterface()) {
            getUserHandler().messageToUser("read");
            getUserHandler().messageToUser("Choose your family member");
        }
        StringTyper stringTyper2 = new StringTyper(this);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(stringTyper2);
        int i = -1;
        while(!typed && !timeIsOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
            }
        }
        if(timeIsOver){
            timeIsOver = false;
            if(!getUserHandler().isGuiInterface()) {
                getUserHandler().messageToUser("read");
            }
            return null;
        }
        if(typed){
            typed = false;
            i = typedInt;
        }
        FamilyMember chosen;
        try {
            chosen = this.familyMembers[i];
            if(chosen == null){
                if(!getUserHandler().isGuiInterface()) {
                    getUserHandler().messageToUser("You already used this family member, choose another one");
                }
                return chooseFamilyMember(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if(!getUserHandler().isGuiInterface()) {
                getUserHandler().messageToUser("Invalid number");
            }
            return chooseFamilyMember(0);
        }
        if(!getUserHandler().isGuiInterface()) {
            getUserHandler().messageToUser("You choose the " + i + " family member");
            getUserHandler().messageToUser("You have " + this.getResources().toString());
        }
        return chosen;
    }

    public LeaderCard chooseLeaderCard() throws RemoteException {
        LeaderCard leaderCard = null;
        userHandler.messageToUser("Chooose the leader card:");
        for (int i = 0; i < leaderCards.size(); i++) {
            userHandler.messageToUser(i+". "+ leaderCards.get(i).toString());
        }
        userHandler.messageToUser("write");
        String answer = userHandler.messageFromUser();
        int i = -1;
        try {
            i = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            userHandler.messageToUser("Is not a number, try again");
            chooseLeaderCard();
        }
        if (i < leaderCards.size()) {
            leaderCard = leaderCards.get(i);
        } else {
            userHandler.messageToUser("Invalid number, try again");
            chooseLeaderCard();
        }

        return leaderCard;


    }

    public UserHandler getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public ArrayList<PermanentEffect> getPermanentEffects() {
        return permanentEffects;
    }

    public boolean isTimeIsOver() {
        return timeIsOver;
    }

    public boolean isTyped() {
        return typed;
    }

    public int getTypedInt() {
        return typedInt;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }
}

