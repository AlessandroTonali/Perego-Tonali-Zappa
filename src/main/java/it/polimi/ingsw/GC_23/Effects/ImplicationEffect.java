package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 21/05/17.
 */
public class ImplicationEffect extends AbsEffect{
    private ArrayList<SingleCost> requirements;
    private ArrayList<AbsEffect> givings;

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
        int i;
        String string;
        Boolean madeChoice = false;
        ArrayList<SingleCost> chosenCost = new ArrayList<SingleCost>();
        ArrayList<AbsEffect> chosenBenefit = new ArrayList<AbsEffect>();
        ImplicationEffect chosen = new ImplicationEffect(chosenCost, chosenBenefit);
        while (!madeChoice) {
            player.getUserHandler().messageToUser("Select possible implication");
            for (int m = 0; m < requirements.size(); m++) {
                player.getUserHandler().messageToUser(m + "--> " + requirements.get(m).toString());
                player.getUserHandler().messageToUser("     " + givings.get(m).toString());
            }
            try {
                player.getUserHandler().messageToUser("write");
                string = player.getUserHandler().messageFromUser();
                i = Integer.parseInt(string);
                if(i<this.getGivings().size()){
                    player.getUserHandler().messageToUser("Chosen implication");
                }
                else{
                    player.getUserHandler().messageToUser("Error: incorrect number, try again");
                    continue;
                }
            }catch (NumberFormatException e) {
                player.getUserHandler().messageToUser("Invalid implication effect, please try again");
                continue;
            }
            try{
                chosen.getRequirements().add(this.getRequirements().get(i));
                chosen.getGivings().add(this.getGivings().get(i));
                player.getUserHandler().messageToUser("You pay: "+ chosen.getRequirements().toString());
                player.getUserHandler().messageToUser("You get: "+ chosen.getGivings().toString());
                player.getUserHandler().messageToUser("");
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosen;
    }


    public void activeEffect(Player player) throws IOException {
        ImplicationEffect implicationEffect = chooseImplication(player);
        SingleCost cost = implicationEffect.getRequirements().get(0);
        AbsEffect effect = implicationEffect.getGivings().get(0);
        player.getResources().pay(cost.getResources());
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
