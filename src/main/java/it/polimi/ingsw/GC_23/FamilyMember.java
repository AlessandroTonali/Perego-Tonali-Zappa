package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class FamilyMember {
    private PlayerColor playerColor;
    private FamilyColor familyColor;
    private int value;
    private ActionSpace position;

    public FamilyMember(PlayerColor playerColor, FamilyColor familyColor) {
        this.playerColor = playerColor;
        this.familyColor = familyColor;
        this.value = 0;
        this.position = null;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
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
}


