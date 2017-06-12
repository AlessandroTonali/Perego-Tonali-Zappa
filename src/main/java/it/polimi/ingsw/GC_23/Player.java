package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Connection.UserHandler;
import it.polimi.ingsw.GC_23.Controller.*;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;

import java.io.Console;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Player implements Serializable {
    private Board view;
    private PlayerColor playerColor;
    private ResourcesSet resources;
    private CardOfPlayer cardOfPlayer;
    private BonusTile bonusTile;
    private FamilyMember[] familyMembers;
    private PermanentEffect permanentEffect;
    private Scanner scan;
    private UserHandler userHandler;
    private PrintWriter outWriter;

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

    public Board getView() {
        return view;
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

    public void chooseMove(Board board) {
        //momentaneo
        this.view = board;
        System.out.println("press 0 for placing a familiar in council\n" +
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
        Scanner scan = new Scanner(System.in);
        String sw = scan.nextLine();

        int i;
        try {
            i = Integer.parseInt(sw);

        } catch (NumberFormatException e) {
            System.out.println("Invalid format");
            i = -1;

        }
        switch (i) {
            case -1:
                chooseMove(view);
                break;
            case 0:
                new CouncilController(board.getCouncilSpace(), chooseFamilyMember());
                break;
            case 1:
                new HarvestController(chooseFamilyMember(), board.getHarvestSpace());
                break;
            case 2:
                new ProductionController(chooseFamilyMember(),  board.getProductionSpace());
                break;
            case 3:
                String servants = this.getNextLine();
                int j = -1;
                try {
                    j = Integer.parseInt(servants);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                    chooseMove(board);

                }

                new IncreaseFamilyValue( j , chooseFamilyMember());
                break;
            case 4:
                new MarketController(chooseFamilyMember(), board.getMarketSpaces());
                break;
            case 5:
                new TerritoryController(chooseFamilyMember(), board.getTower(0));
                break;
            case 6:
                new OtherCardsController(chooseFamilyMember(), board.chooseTower(this));
                break;
            case 7:
                new OtherCardsController(chooseFamilyMember(0), board.getTower(2));
                break;
            case 8:
                new OtherCardsController(chooseFamilyMember(0), board.getTower(3));
                break;
            case 9:
                System.out.println(view.toString());
                chooseMove(view);
                break;
            case 10:
                System.out.println(this.resources.toString());
                chooseMove(view);
                break;
            case 11:
                break;
            default:
                System.out.println("wrong number selected, try again");
                chooseMove(view);


        }
        //TODO
        return;
    }

    public void chooseMove(Board board, int value) {
        //momentaneo
        this.view = board;
        outWriter.println("press 0 for placing a familiar in council\n" +
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
        outWriter.println("write");
        String sw = scan.nextLine();
        int i;
        try {
            i = Integer.parseInt(sw);
        } catch (NumberFormatException e) {
            outWriter.println("Invalid format");
            i = -1;
        }

        switch (i) {
            case -1:
                chooseMove(view);
                break;
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
                outWriter.println("write");
                String servants = this.getNextLine();
                int j = -1;
                try {
                    j = Integer.parseInt(servants);

                } catch (NumberFormatException e) {
                    outWriter.println("Invalid format");
                    chooseMove(board);
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
                outWriter.println(view.toString());
                chooseMove(view,1);
            case 10:
                outWriter.println(this.resources.toString());
                chooseMove(view,1);
                break;
            case 11:
                break;
            default:
                outWriter.println("Wrong number selected, try again");
                chooseMove(view,1);

        }
        outWriter.println();
        outWriter.println("Wait for your turn\n");
        outWriter.println("wait");
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

        for(FamilyMember f: familyMembers) {
            if( f == null){
                continue;
            }
            System.out.println(f.toString());
        }

        System.out.println("Choose your family member");
        String sw = this.getNextLine();
        System.out.println(sw);
        int i;



        try {
            i = Integer.parseInt(sw);

        } catch (NumberFormatException e) {
            System.out.println("Invalid format");
            return  chooseFamilyMember();
        }
        FamilyMember chosen;


        try {
            chosen = this.familyMembers[i];
            if(chosen == null){
                System.out.println("You already used this family member, choose another one");
                return chooseFamilyMember();
            }
            System.out.println("You have chosen your family member");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number");
            return chooseFamilyMember();

        }


        System.out.println("You choose the " + i + "family member");
        System.out.println("You have " + this.getResources().toString());
        return chosen;
    }

    public FamilyMember chooseFamilyMember(int value) {
        for(FamilyMember f: familyMembers) {
            if( f == null){
                continue;
            }
            outWriter.println(f.toString());
        }
        outWriter.println("Choose your family member");
        outWriter.println("write");
        String sw = scan.nextLine();
        int i;
        try {
            i = Integer.parseInt(sw);

        } catch (NumberFormatException e) {
            outWriter.println("Invalid format");
            return chooseFamilyMember(0);
        }
        FamilyMember chosen;
        try {
            chosen = this.familyMembers[i];
            if(chosen == null){
                outWriter.println("You already used this family member, choose another one");
                return chooseFamilyMember(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            outWriter.println("Invalid number");
            return chooseFamilyMember(0);
        }
        outWriter.println("You choose the " + i + "family member");
        outWriter.println("You have " + this.getResources().toString());
        return chosen;
    }

    public String getNextLine() {
        return scan.nextLine();
    }

    public UserHandler getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public void setScan(){
        this.scan =  this.getUserHandler().getInScanner();
    }

    public void setOutWriter(){
        this.outWriter = this.getUserHandler().getOutWriter();
    }

    public Scanner getScan() {
        return scan;
    }

    public PrintWriter getOutWriter() {
        return outWriter;
    }
}

