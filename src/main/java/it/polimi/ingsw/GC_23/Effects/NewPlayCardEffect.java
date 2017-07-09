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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Alessandro on 06/06/2017.
 */
public class NewPlayCardEffect extends AbsEffect {

    private NewPlayColor towerColor;
    private int diceValue;
    ArrayList<SingleCost> resourcesDiscount;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Select possible discount");
            }
            for (int j = 0; j < resourcesDiscount.size(); j++) {
                player.getUserHandler().messageToUser(j + "--> " + resourcesDiscount.get(j).toString());
            }
            if (player.getUserHandler().isGuiInterface()){
                player.getUserHandler().messageToUser("end");
            }
            ExecutorService executorService = Executors.newCachedThreadPool();
            StringTyper stringTyper  = new StringTyper(player);
            executorService.submit(stringTyper);
            while(!player.isTyped() && !player.isTimeIsOver()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                    Thread.currentThread().interrupt();
                }
            }
            int i = -1;
            if(player.isTimeIsOver()){
                i = 0;
            }
            if(player.isTyped()){
                player.setTyped(false);
                i = player.getTypedInt();
            }


            if (i >= 0 && i < resourcesDiscount.size()) {
                sale = resourcesDiscount.get(i);
            } else {
                sale = chooseResourceDiscount(player);
            }
        }
        return sale;
    }

    public Tower chooseTower(Player player) throws RemoteException {
        Tower[] towers = player.getView().getTowers();
        if(!player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("Choose a tower");
        }
        int i = -1;
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper  = new StringTyper(player);
        executorService.submit(stringTyper);
        while(!player.isTyped() && !player.isTimeIsOver()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
        }
        if(player.isTimeIsOver()){
            i = 0;
        }
        if(player.isTyped()){
            player.setTyped(false);
            i = player.getTypedInt();
        }


        try{
            return towers[i];
        }catch (NullPointerException e) {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Number out of bound, insert again");
            }
            return chooseTower(player);
        }
    }


    @Override
    public void activeEffect(Player player) throws IOException {
        if(player.getUserHandler().isGuiInterface()){
            player.getUserHandler().messageToUser("newPlayCardEffect");
        }
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
                tower = chooseTower(player);
                break;
        }

        NewPlayCardController newPlay = new NewPlayCardController(tower, familyMember, sale);

        if (newPlay.isLegal()) {
            newPlay.makeAction();
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("New play card effect done");
            }
        } else {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Error new play card effect");
            }
            boolean stayInWhile = true;
            while (stayInWhile) {
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("What do you want to do? \n " +
                            "1. Try again \n" +
                            "2. Discard");
                }
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    StringTyper stringTyper  = new StringTyper(player);
                    executorService.submit(stringTyper);
                    while(!player.isTyped() && !player.isTimeIsOver()){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            logger.setLevel(Level.SEVERE);
                            logger.severe(String.valueOf(e));
                            Thread.currentThread().interrupt();
                        }
                    }
                    int input = -1;
                    if(player.isTimeIsOver()){
                        input = 2;
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
                            if(!player.getUserHandler().isGuiInterface()) {
                                player.getUserHandler().messageToUser("Invalid choice");
                            }
                            break;
                    }

            }
        }
    }

}

