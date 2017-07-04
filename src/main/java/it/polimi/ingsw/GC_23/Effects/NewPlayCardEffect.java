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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    public SingleCost chooseResourceDiscount(Player player) throws RemoteException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper = new StringTyper(player);
        String string;
        Boolean madeChoice= false;
        ArrayList<SingleCost> chosenDiscount = new ArrayList<SingleCost>();
        while(!madeChoice){
            player.getUserHandler().messageToUser("Select possible discount");
            for(int n=0; n< resourcesDiscount.size(); n++ ){
                player.getUserHandler().messageToUser(n+"--> "+resourcesDiscount.get(n).toString());
            }
            int i = -1;
            while (!player.isTimeIsOver() && !player.isTyped()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(player.isTimeIsOver()){
                player.setTimeIsOver(false);
                player.getUserHandler().messageToUser("read");
                return  null;
            }
            if(player.isTyped()){
                player.setTyped(false);
                i = player.getTypedInt();
            }
            if(i< resourcesDiscount.size()){
                player.getUserHandler().messageToUser("Chosen discount");
            }
            else{
                player.getUserHandler().messageToUser("Error: incorrect number, try again");
                continue;
            }
            try{
                chosenDiscount.add(resourcesDiscount.get(i));
                player.getUserHandler().messageToUser("You get: "+ chosenDiscount.get(i).toString());
                player.getUserHandler().messageToUser("");
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosenDiscount.get(0);
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
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper = new StringTyper(player);
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
               int input = -1;
                while (!player.isTimeIsOver() && !player.isTyped()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(player.isTimeIsOver()){
                    player.setTimeIsOver(false);
                    player.getUserHandler().messageToUser("read");
                    return;
                }
                if(player.isTyped()){
                    player.setTyped(false);
                    input = player.getTypedInt();
                }
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
            }
        }
    }

}

