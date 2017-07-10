package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.Effects.SetDiceEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class FamilyMember {
    private Player player;
    private FamilyColor familyColor;
    private int value;
    private ActionSpace position;

    public FamilyMember(ActionSpace position){
        this.player=null;
        this.familyColor=null;
        this.value=0;
        this.position= position;
    }

    public FamilyMember(Player player, FamilyColor familyColor, int value) {
        this.player = player;
        this.familyColor = familyColor;
        this.value = value;
        this.position = null;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public FamilyColor getFamilyColor() {
        return familyColor;
    }

    public int getValue() {
        return value;
    }

    public ActionSpace getPosition() {
        return position;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * method for check the permanent effect relative on family member
     * @param value initial family member value
     * @return new value of family member
     * @throws RemoteException
     */
    public int checkPermanentEffect(int value) throws RemoteException {
        ArrayList<PermanentEffect> permanentEffectArrayList = player.getPermanentEffects();

        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);

            if (permanentEffect instanceof SetDiceEffect && ("dice_color".equals(((SetDiceEffect) permanentEffectArrayList.get(i)).getType()) && familyColor != FamilyColor.NEUTRAL)) {
                value = ((SetDiceEffect) permanentEffect).getValue();
            }

            if (permanentEffect instanceof SetDiceEffect && ("dice_neutral".equals(((SetDiceEffect) permanentEffectArrayList.get(i)).getType()) && familyColor == FamilyColor.NEUTRAL)) {
                value = ((SetDiceEffect) permanentEffect).getValue();
            }
        }

        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof PlusDiceEffect && ("dice_color".equals(((PlusDiceEffect) permanentEffectArrayList.get(i)).getType()) && familyColor != FamilyColor.NEUTRAL)) {
                value = value + ((PlusDiceEffect) permanentEffect).getPlusDiceValue();

            }
            if (permanentEffect instanceof PlusDiceEffect && ("dice_neutral".equals(((PlusDiceEffect) permanentEffectArrayList.get(i)).getType()) && familyColor == FamilyColor.NEUTRAL)) {
                value = value + ((PlusDiceEffect) permanentEffect).getPlusDiceValue();
            }
        }
        return value;
    }

    public void setPosition(ActionSpace position) {
        this.position = position;
    }

    public void increaseFamilyValue(int quantity){
        this.value = this.value + quantity;
        if(getPlayer().isDoubleServantToIncrease()) {
            quantity = quantity * 2;
        }
        this.player.getResources().setServants(this.player.getResources().getServants()- quantity);
    }

    public boolean isEquals(FamilyMember familyMember) {
        boolean equal = false;
        if (this.player.isEquals(familyMember.getPlayer()) && this.familyColor.equals(familyMember.getFamilyColor()) && this.value == familyMember.getValue()) {
            equal = true;
        }

        return equal;
    }

    @Override
    public String toString(){
       try{
           return "Player " + this.player.getPlayerColor() + " color " + this.familyColor + " value " +
                   this.value;
       } catch (NullPointerException e) {
           return "nooooooooooo" +
                   " family member";
       }
    }


    public void setFamilyColor(FamilyColor familyColor) {
        this.familyColor = familyColor;
    }
}


