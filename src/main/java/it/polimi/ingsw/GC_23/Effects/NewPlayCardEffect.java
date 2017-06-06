package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

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
                if(i< resourcesDiscount.size()){
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
                chosenDiscount.add(resourcesDiscount.get(i));
                System.out.println("You get: "+ chosenDiscount.get(i).toString());
                System.out.println();
                madeChoice =true;
            }catch (NullPointerException e){
                return null;
            }
        }
        return chosenDiscount.get(0);
    }


    @Override
    public void activeEffect(Player player) {
        //TODO: giocata (nella tower) senza mettere il family member, chiamerà isLegal di NewPlay e il suo makeMove
    }

    @Override
    public int getTypeEffect() {
        return 0;
    }
}

