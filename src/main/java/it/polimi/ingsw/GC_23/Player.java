package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Controller.*;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Player implements Serializable {
    private PlayerColor playerColor;
    private ResourcesSet resources;
    private CardOfPlayer cardOfPlayer;
    private BonusTile bonusTile;
    private FamilyMember[] familyMembers;
    private PermanentEffect permanentEffect;
    private Scanner scan;

    public Player(PlayerColor playerColor, BonusTile bonusTile) {
        this.playerColor = playerColor;
        // bisogna usare il design pattern della fabbrica per dare le risorse giuste in base ai player
        this.bonusTile = bonusTile;
        this.cardOfPlayer = new CardOfPlayer();
        this.scan = new Scanner(System.in);
        //bisogna decidere come istanziare i family member
        // permanent effect ancora non lo dobbiamo fare

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

    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
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

    public void chooseMove() {
        //momentaneo
        Board board = new Board(4);
        System.out.println("press\n 0 for placing a familiar in council\n" +
                "press 1 for getting the harvest\n" +
                "press 2 for getting production\n" +
                "press 3 for increasing your familiar value\n" +
                "press 4 for for going in the market\n" +
                "press 5 for going in a tower");
        Scanner scan = new Scanner(System.in);
        String sw = scan.nextLine();

        int i;
        try {
            i = Integer.parseInt(sw);

        } catch (NumberFormatException e) {
            System.out.println("Invalid format");
            i = -1;

        }

        if (i > 8) {
            System.out.println("Invalid number");
            chooseMove();
        }


        switch (i) {
            case -1:
                break;
            case 0:
                new CouncilController(board.getCouncilSpace(), chooseFamilyMember());
                break;
            case 1:
                new HarvestController(chooseFamilyMember(), board.getHarvestSpace());
                break;
            case 2:
                new ProductionController(chooseFamilyMember(), new ProductionSpace());
                break;
            case 3:
                new IncreaseFamilyValue(5, chooseFamilyMember());
                break;
            case 4:
                new MarketController(chooseFamilyMember(), new MarketSpace(null));
                break;
            case 5:
                new TerritoryController(chooseFamilyMember(), board.getTower(0));
                break;
            case 6: break;//todo toglilo
            default:
                new OtherCardsController(chooseFamilyMember(), board.chooseTower(this));
                break;

        }


        //TODO
        return;
    }

    public boolean isEquals(Player player) {
        if (this.playerColor == player.getPlayerColor()) {
            return true;
        } else {
            return false;
        }

    }

    public FamilyMember chooseFamilyMember() {

        System.out.println("Choose your family member");
        String sw = this.getNextLine();
        System.out.println(sw);
        int i;

        try {
            i = Integer.parseInt(sw);
            System.out.println("You have chosen your family member");

        } catch (NumberFormatException e) {
            System.out.println("Invalid format");
            return null;
        }
        FamilyMember chosen;


        try {
            chosen = this.familyMembers[i];
        } catch (NullPointerException e) {
            return null;
        }


        System.out.println("You choose the " + i + "family member");
        System.out.println("you have " + this.getResources().toString());
        return chosen;
    }

    public String getNextLine() {
        return scan.nextLine();
    }

}

