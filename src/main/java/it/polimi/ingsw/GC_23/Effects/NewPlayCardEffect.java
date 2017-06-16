package it.polimi.ingsw.GC_23.Effects;

import com.sun.media.sound.InvalidFormatException;
import it.polimi.ingsw.GC_23.*;
import it.polimi.ingsw.GC_23.Controller.NewPlay;
import it.polimi.ingsw.GC_23.Controller.OtherCardsController;
import it.polimi.ingsw.GC_23.Controller.TerritoryController;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
import it.polimi.ingsw.GC_23.Spaces.Tower;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public void activeEffect(Player player) throws IOException {
        //TODO: giocata (nella tower) senza mettere il family member, chiamerà isLegal di NewPlay e il suo makeMove
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

        NewPlay newPlay = new NewPlay(tower, familyMember, sale);

        if (newPlay.isLegal()) {
            newPlay.makeAction();
            System.out.println("New play card effect done");
        } else {
            System.out.println("Error new play card effect");
            boolean stayInWhile = true;
            while (stayInWhile) {
                System.out.println("What do you want to do? \n " +
                        "1. Try again \n" +
                        "2. Discard");
                Scanner scanner = new Scanner(System.in);
                try {
                    int input = Integer.parseInt(scanner.nextLine());
                    switch (input) {
                        case 1:
                            stayInWhile = false;
                            this.activeEffect(player);
                            break;
                        case 2:
                            stayInWhile = false;
                            break;
                        default:
                            System.out.println("Invalid choise");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
        }



    }

}

