package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.StringTyper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 21/05/17.
 */
public class DiscountEffect extends AbsEffect{
    private ArrayList<SingleCost> resourcesDiscount;
    private int[] valueDiscount;
    private CardColor color;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public DiscountEffect(ArrayList<SingleCost> discount, int[] value, CardColor color) {
        this.resourcesDiscount = discount;
        this.valueDiscount = value;
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public ArrayList<SingleCost> getResourcesDiscount() {
        return resourcesDiscount;
    }

    public void setResourcesDiscount(ArrayList<SingleCost> resourcesDiscount) {
        this.resourcesDiscount = resourcesDiscount;
    }

    public int[] getValueDiscount() {
        return valueDiscount;
    }

    public void setValueDiscount(int[] valueDiscount) {
        this.valueDiscount = valueDiscount;
    }

    public SingleCost chooseResourceDiscount(Player player) throws RemoteException {
        int i = -1;
        String string;
        Boolean madeChoice= false;
        ArrayList<SingleCost> chosenDiscount = new ArrayList<SingleCost>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper = new StringTyper(player);
        while(!madeChoice){
            player.getUserHandler().messageToUser("Select possible discount");
            for(int n=0; n< resourcesDiscount.size(); n++ ){
                player.getUserHandler().messageToUser(n+"--> "+resourcesDiscount.get(n).toString());
            }
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
                player.getUserHandler().messageToUser("read");
                return  null;
            }
            if(player.isTyped()){
                player.setTyped(false);
                i = player.getTypedInt();
            }
            if(i<this.getResourcesDiscount().size()){
                player.getUserHandler().messageToUser("Chosen discount");
            }
                else{
                    player.getUserHandler().messageToUser("Error: incorrect number, try again");
                    continue;
            }
            try{
                chosenDiscount.add(this.getResourcesDiscount().get(i));
                player.getUserHandler().messageToUser("You get: "+ chosenDiscount.get(i).toString());
                player.getUserHandler().messageToUser("");
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosenDiscount.get(0);
    }

    //todo: sconto dei valori del familiare per effetti permanenti
    public void activeEffect(Player player) throws RemoteException {
    SingleCost chosenResourcesDiscount = chooseResourceDiscount(player);
    //è uguale se li aggiungo e dopo li toglie come se il prezzo fosse uguale?
    player.getResources().sum(chosenResourcesDiscount.getResources(),player);
    }


}
