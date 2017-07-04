package it.polimi.ingsw.GC_23.Effects;

import com.sun.media.sound.InvalidFormatException;
import it.polimi.ingsw.GC_23.*;
import it.polimi.ingsw.GC_23.Controller.NewPlayCardController;
import it.polimi.ingsw.GC_23.Controller.OtherCardsController;
import it.polimi.ingsw.GC_23.Controller.TerritoryController;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
import it.polimi.ingsw.GC_23.Spaces.Tower;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Alessandro on 06/06/2017.
 */
public class NewPlayCardEffect extends AbsEffect {

    private NewPlayColor towerColor;
    private int diceValue;
    ArrayList<SingleCost> resourcesDiscount;


    public NewPlayCardEffect(int diceValue, NewPlayColor towerColor, ArrayList<SingleCost> resourcesDiscount) {
        this.diceValue = diceValue;
        this.resourcesDiscount = resourcesDiscount;
        this.towerColor = towerColor;

    }

    public SingleCost chooseResourceDiscount(Player player) throws IOException {
        SingleCost sale;
        ArrayList<SingleCost> chosenDiscount = new ArrayList<SingleCost>();
        if (chosenDiscount.size() == 1) {
            sale =  resourcesDiscount.get(0);
        } else {
            player.getUserHandler().messageToUser("Select possible discount");
            for (int j = 0; j < resourcesDiscount.size(); j++) {
                player.getUserHandler().messageToUser(j + "--> " + resourcesDiscount.get(j).toString());
            }
            player.getUserHandler().messageToUser("write");
            String answer = player.getUserHandler().messageFromUser();
            int i;
            try {
                i = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                player.getUserHandler().messageToUser("Invalid format");
                i = -1;
            }
            if (i >= 0 && i < resourcesDiscount.size()) {
                sale = resourcesDiscount.get(i);
            } else {
                sale = chooseResourceDiscount(player);
            }
        }
        return sale;
    }


    @Override
    public void activeEffect(Player player) throws IOException {
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.NEUTRAL, diceValue);
        SingleCost sale = chooseResourceDiscount(player);
        Tower tower = null;
        switch (towerColor) {
            case GREEN:
                tower = player.getView().getTower(0);
                break;
            case BLUE:
                tower = player.getView().getTower(1);
                break;
            case YELLOW:
                tower = player.getView().getTower(2);
                break;
            case PURPLE:
                tower = player.getView().getTower(3);
                break;
            case RAINBOW:
                tower = player.getView().chooseTower(player);
                break;
        }

        NewPlayCardController newPlay = new NewPlayCardController(tower, familyMember, sale);

        if (newPlay.isLegal()) {
            newPlay.makeAction();
            player.getUserHandler().messageToUser("New play card effect done");
        } else {
            player.getUserHandler().messageToUser("Error new play card effect");
            boolean stayInWhile = true;
            while (stayInWhile) {
                player.getUserHandler().messageToUser("What do you want to do? \n " +
                        "1. Try again \n" +
                        "2. Discard");
                try {
                    player.getUserHandler().messageToUser("write");
                    String string = player.getUserHandler().messageFromUser();
                    int input = Integer.parseInt(string);
                    switch (input) {
                        case 1:
                            stayInWhile = false;
                            this.activeEffect(player);
                            break;
                        case 2:
                            stayInWhile = false;
                            break;
                        default:
                            player.getUserHandler().messageToUser("Invalid choise");
                            break;
                    }
                } catch (NumberFormatException e) {
                    player.getUserHandler().messageToUser("Invalid input");
                }
            }
        }
    }

}

