package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.MilitaryCost;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro on 19/06/2017.
 */
public class PlusDiceEffect extends PermanentEffect {

    private int plusDiceValue;
    private String type;
    private CardColor cardColor;
    private ArrayList<SingleCost> sales;

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

    public SingleCost chooseSale(Player player) throws RemoteException {
        int i = 0;
        int j = 0;
        if (sales.size() > 1) {
            for (SingleCost singlecost : sales) {
                if (!(singlecost instanceof MilitaryCost)) {
                    player.getUserHandler().messageToUser("Press " + i + " for choosing: " + singlecost.getResources().toString());
                    i++;
                } else {
                    player.getUserHandler().messageToUser("Press " + i + " for choosing: " + singlecost.getResources().toString() + " and you required " + ((MilitaryCost) singlecost).getResourcesRequired().toString());
                    i++;
                }
            }
            player.getUserHandler().messageToUser("write");
            String sw = player.getUserHandler().messageFromUser();
            try {
                j = Integer.parseInt(sw);
                player.getUserHandler().messageToUser("Chosen sale");

            } catch (NumberFormatException e) {
                player.getUserHandler().messageToUser("Invalid format, try again");
                chooseSale(player);
            }
        }
        return sales.get(j);


    }

    @Override
    public void activeEffect(Player player) throws IOException {

    }
}
