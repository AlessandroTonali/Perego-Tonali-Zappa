package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 21/05/17.
 */
public class ImplicationEffect extends AbsEffect{
    private ArrayList<SingleCost> requirements;
    private ArrayList<AbsEffect> givings;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ImplicationEffect(ArrayList<SingleCost> requirements, ArrayList<AbsEffect> givings) {
        this.requirements = requirements;
        this.givings = givings;
    }

    public void setRequirements(ArrayList<SingleCost> requirements) {
        this.requirements = requirements;
    }

    public void setGivings(ArrayList<AbsEffect> givings) {
        this.givings = givings;
    }

    public ArrayList<SingleCost> getRequirements() {
        return requirements;
    }

    public ArrayList<AbsEffect> getGivings() {
        return givings;
    }

    public ImplicationEffect chooseImplication(Player player) throws RemoteException {
        Boolean madeChoice = false;
        ArrayList<SingleCost> chosenCost = new ArrayList<SingleCost>();
        ArrayList<AbsEffect> chosenBenefit = new ArrayList<AbsEffect>();
        ImplicationEffect chosen = new ImplicationEffect(chosenCost, chosenBenefit);
        while (!madeChoice) {
            int i =-1;
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Select possible implication");
            }
            for (int m = 0; m < requirements.size(); m++) {
                player.getUserHandler().messageToUser(m + "--> " + requirements.get(m).toString());
                player.getUserHandler().messageToUser("     " + givings.get(m).toString());
            }
            if(player.getUserHandler().isGuiInterface()){
                player.getUserHandler().messageToUser("end");
            }
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
                i = player.getTypedInt();
            }
                if(i<this.getGivings().size()){
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("Chosen implication");
                    }
                }
                else{
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("Error: incorrect number, try again");
                    }
                    continue;
                }

            try{
                chosen.getRequirements().add(this.getRequirements().get(i));
                chosen.getGivings().add(this.getGivings().get(i));
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("You pay: " + chosen.getRequirements().toString());
                    player.getUserHandler().messageToUser("You get: " + chosen.getGivings().toString());
                    player.getUserHandler().messageToUser("");
                }
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosen;
    }


    @Override
    public void activeEffect(Player player) throws IOException {
        if(player.getUserHandler().isGuiInterface()){
            player.getUserHandler().messageToUser("implicationEffect");
        }
        ImplicationEffect implicationEffect = chooseImplication(player);
        SingleCost cost = implicationEffect.getRequirements().get(0);
        AbsEffect effect = implicationEffect.getGivings().get(0);
        player.getResources().pay(cost.getResources());
        if(!player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("You lost:" + cost.toString());
        }
        effect.activeEffect(player);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < requirements.size(); i++) {
            stringBuilder.append("pay: " + requirements.get(i) + " to get: " + givings.get(i));
        }
        return String.valueOf(stringBuilder);
    }
}
