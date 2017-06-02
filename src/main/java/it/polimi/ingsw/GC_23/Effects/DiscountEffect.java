package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

/**
 * Created by jesss on 21/05/17.
 */
public class DiscountEffect extends AbsEffect{
    private ArrayList<SingleCost> resourcesDiscount;
    private int[] valueDiscount;
    private CardColor color;

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

    public SingleCost chooseResourceDiscount(Player player){
        int i;
        String string;
        Boolean madeChoice= false;
        ArrayList<SingleCost> chosenDiscount = new ArrayList<SingleCost>();
        while(!madeChoice){
            System.out.println("Select possible discount");
            for(int n=0; n< resourcesDiscount.size(); n++ ){
                System.out.println(n+"--> "+resourcesDiscount.get(n).toString());
            }
            try {
                string = player.getNextLine();
                i = Integer.parseInt(string);
                if(i<this.getResourcesDiscount().size()){
                    System.out.println("Chosen discount");
                }
                else{
                    System.out.println("Error: incorrect number, try again");
                    continue;
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid discount effect, please try again");
                continue;
            }
            try{
                chosenDiscount.add(this.getResourcesDiscount().get(i));
                System.out.println("You get: "+ chosenDiscount.get(i).toString());
                System.out.println();
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosenDiscount.get(0);
    }

    public void activeEffect(Player player) {
    SingleCost chosenResourcesDiscount = chooseResourceDiscount(player);
    //TODO
    }

    @Override
    public int getTyteEffect() {
        return EffectType.DISCOUNT_EFFECT_TYPE;
    }
}
