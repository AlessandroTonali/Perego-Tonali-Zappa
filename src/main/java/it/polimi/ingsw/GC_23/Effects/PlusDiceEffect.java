package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.*;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro on 19/06/2017.
 */
public class PlusDiceEffect extends PermanentEffect {

    private int plusDiceValue;
    private String type;
    private CardColor cardColor;
    private ArrayList<SingleCost> sales;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * effect that give you a plus on a specific dice or a set of dices
     * @param plusDiceValue value to sum at value of dice
     * @param type of plus
     * @param cardColor color of tower that receive the effect, it is possible to set a null
     * @param sales on card cost
     */
    public PlusDiceEffect(int plusDiceValue, String type, CardColor cardColor, ArrayList<SingleCost> sales) {
        this.plusDiceValue = plusDiceValue;
        this.type = type;
        this.cardColor = cardColor;
        this.sales = sales;
    }

    public int getPlusDiceValue() {
        return plusDiceValue;
    }

    public String getType() {
        return type;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public ArrayList<SingleCost> getSales() {
        return sales;
    }

    /**
     * method for choosing sale on a plus dice effect
     * @param player that active the effect
     * @return the sale chosen
     * @throws RemoteException
     */
    public SingleCost chooseSale(Player player) throws RemoteException {
        int i = 0;
        int j = 0;
        if (sales.size() > 1) {
            for (SingleCost singlecost : sales) {
                if (!(singlecost instanceof MilitaryCost)) {
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("Press " + i + " for choosing: " + singlecost.getResources().toString());
                    }
                    i++;
                } else {
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("Press " + i + " for choosing: " + singlecost.getResources().toString() + " and you required " + ((MilitaryCost) singlecost).getResourcesRequired().toString());
                    }
                    i++;
                }
            }
            ExecutorService executorService = Executors.newCachedThreadPool();
            StringTyper stringTyper = new StringTyper(player);
            executorService.submit(stringTyper);
            while (!player.isTimeIsOver() && !player.isTyped()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                    Thread.currentThread().interrupt();
                }
            }
            if(player.isTimeIsOver()){
                player.setTimeIsOver(false);
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("read");
                }
                return  null;
            }
            if(player.isTyped()){
                player.setTyped(false);
                j = player.getTypedInt();
            }
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Chosen sale");
            }
        }
        return sales.get(j);


    }

    /**
     *
     * @param player that want to active the effect
     * @throws IOException
     */
    @Override
    public void activeEffect(Player player) throws IOException {
        if(player.getUserHandler().isGuiInterface()){
            player.getUserHandler().messageToUser("plusDiceEffect");
        }
        FamilyMember[] familyMembers = player.getFamilyMembers();
        for (int i = 0; i < familyMembers.length; i++) {
            FamilyMember familyMember = familyMembers[i];
            if (type.equals("dice_neutral") && familyMember.getFamilyColor() == FamilyColor.NEUTRAL) {
                familyMember.setValue(familyMember.getValue() + plusDiceValue);
            }
            if (type.equals("dice_color") && familyMember.getFamilyColor() != FamilyColor.NEUTRAL) {
                familyMember.setValue(familyMember.getValue() + plusDiceValue);
            }
        }
    }
}
