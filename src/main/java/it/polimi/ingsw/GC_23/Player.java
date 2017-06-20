package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Connection.UserHandler;
import it.polimi.ingsw.GC_23.Controller.*;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
    private boolean timeIsOver = false;
    private boolean typed = false;
    private int typedInt;

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


    public void chooseMove(Board board, int value) throws IOException {
        this.view = board;
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
                "press 11 to skip");
        getUserHandler().messageToUser("write");
        String sw = getUserHandler().messageFromUser();
        int i;
        try {
            i = Integer.parseInt(sw);
        } catch (NumberFormatException e) {
            getUserHandler().messageToUser("Invalid format");
            i = -1;
        }

        switch (i) {
            case -1:
                chooseMove(view, 0);
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
                getUserHandler().messageToUser("How much?");
                getUserHandler().messageToUser("write");
                String servants = getUserHandler().messageFromUser();
                int j = -1;
                try {
                    j = Integer.parseInt(servants);

                } catch (NumberFormatException e) {
                    getUserHandler().messageToUser("Invalid format");
                    chooseMove(board, 0);
                    return;
                }
                new IncreaseFamilyValue( j , chooseFamilyMember(0));
                break;
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
                chooseMove(view,1);
                return;
            case 10:
                getUserHandler().messageToUser(this.resources.toString());
                getUserHandler().messageToUser("");
                chooseMove(view,1);
                return;
            case 11:
                break;
            default:
                getUserHandler().messageToUser("Wrong number selected, try again");
                chooseMove(view,1);
                return;

        }
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
            getUserHandler().messageToUser("Press " + j + " for choosing: " + f.toString());
            j++;
        }
        getUserHandler().messageToUser("Choose your family member");
        getUserHandler().messageToUser("write");
        String sw = getUserHandler().messageFromUser();
        int i;
        try {
            i = Integer.parseInt(sw);

        } catch (NumberFormatException e) {
            getUserHandler().messageToUser("Invalid format");
            return chooseFamilyMember(0);
        }
        FamilyMember chosen;
        try {
            chosen = this.familyMembers[i];
            if(chosen == null){
                getUserHandler().messageToUser("You already used this family member, choose another one");
                return chooseFamilyMember(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            getUserHandler().messageToUser("Invalid number");
            return chooseFamilyMember(0);
        }
        getUserHandler().messageToUser("You choose the " + i + " family member");
        getUserHandler().messageToUser("You have " + this.getResources().toString());
        return chosen;
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


}

