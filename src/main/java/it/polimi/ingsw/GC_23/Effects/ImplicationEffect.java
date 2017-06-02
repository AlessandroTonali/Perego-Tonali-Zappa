package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

/**
 * Created by jesss on 21/05/17.
 */
public class ImplicationEffect extends AbsEffect{
    private ArrayList<SingleCost> requirements;
    private ArrayList<BenefitsEffect> givings;

    public ImplicationEffect(ArrayList<SingleCost> requirements, ArrayList<BenefitsEffect> givings) {
        this.requirements = requirements;
        this.givings = givings;
    }

    public void setRequirements(ArrayList<SingleCost> requirements) {
        this.requirements = requirements;
    }

    public void setGivings(ArrayList<BenefitsEffect> givings) {
        this.givings = givings;
    }

    public ArrayList<SingleCost> getRequirements() {
        return requirements;
    }

    public ArrayList<BenefitsEffect> getGivings() {
        return givings;
    }

    public ImplicationEffect chooseImplication(Player player) {
        int i;
        String string;
        Boolean madeChoice = false;
        ArrayList<SingleCost> chosenCost = new ArrayList<SingleCost>();
        ArrayList<BenefitsEffect> chosenBenefit = new ArrayList<BenefitsEffect>();
        ImplicationEffect chosen = new ImplicationEffect(chosenCost, chosenBenefit);
        while (!madeChoice) {
            System.out.println("Select possible implication");
            for (int m = 0; m < requirements.size(); m++) {
                System.out.println(m + "--> " + requirements.get(m).toString());
                System.out.println("     " + givings.get(m).toString());
            }
            try {
                string = player.getNextLine();
                i = Integer.parseInt(string);
                if(i<this.getGivings().size()){
                    System.out.println("Chosen implication");
                }
                else{
                    System.out.println("Error: incorrect number, try again");
                    continue;
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid implication effect, please try again");
                continue;
            }
            try{
                chosen.getRequirements().add(this.getRequirements().get(i));
                chosen.getGivings().add(this.getGivings().get(i));
                System.out.println("You pay: "+ chosen.getRequirements().toString());
                System.out.println("You get: "+ chosen.getGivings().toString());
                System.out.println();
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosen;
    }


    public void activeEffect(Player player) {
        ImplicationEffect implicationEffect = chooseImplication(player);
        SingleCost cost = implicationEffect.getRequirements().get(0);
        BenefitsEffect effect = implicationEffect.getGivings().get(0);
        player.getResources().pay(cost.getResources());
        effect.activeEffect(player);
    }

    @Override
    public int getTypeEffect() {
        return EffectType.IMPLICATION_EFFECT_TYPE;
    }
}
