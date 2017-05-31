package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

import java.util.Scanner;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class FamilyMember {
    private Player player;
    private FamilyColor familyColor;
    private int value;
    private ActionSpace position;

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

    public void setPosition(ActionSpace position) {
        this.position = position;
    }

    public void increaseFamilyValue(int quantity){
        this.value = this.value + quantity;
        this.player.getResources().setServants(this.player.getResources().getServants()- quantity);
    }

    public boolean isEquals(FamilyMember familyMember) {
        boolean equal = false;
        if (this.player.isEquals(familyMember.getPlayer())) {
            if (this.familyColor.equals(familyMember.getFamilyColor())) {
                if (this.value == familyMember.getValue()) {
                    equal = true;
                }
            }
        }

        return equal;
    }

    public String toString(){
        return "Player " + this.player.getPlayerColor() + " color " + this.familyColor + " value " +
                this.value;
    }


}


