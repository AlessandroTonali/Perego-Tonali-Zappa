package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Tower {
    private int DIM=3;
    private TowerSpace[] spaces = new TowerSpace[DIM];

    public TowerSpace[] getSpaces() {
        return spaces;
    }

    public int getDIM() {
        return DIM;
    }

    public void setDIM(int DIM) {
        this.DIM = DIM;
    }

    public void setSpaces(TowerSpace[] spaces) {
        this.spaces = spaces;
    }

    public Tower(TowerSpace[] spaces) {
        this.spaces = spaces;
    }

    //controlla se c'è un altro familiare dello stesso colore nella tower
    public boolean isFamiliar(FamilyMember familyMember) {
        for(int i = 0; i<DIM; i++) {
            if (familyMember.getPlayer() == spaces[i].getFamilyMember().getPlayer()) {
                return true;
            }
        }

            return  false;
    }

}

