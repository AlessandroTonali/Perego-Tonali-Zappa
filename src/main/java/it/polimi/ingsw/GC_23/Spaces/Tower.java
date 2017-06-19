package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Tower {
    private int DIM=3;
    private TowerSpace[] spaces = new TowerSpace[DIM];
    private CardColor towerColor;

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

    public Tower(TowerSpace[] spaces, CardColor towerColor) {
        this.spaces = spaces;
        this.towerColor = towerColor;
    }


    // controlla se è presente un familiare del tuo colore nella torre
    public boolean checkFamiliarTower(FamilyMember familyMember) {
        boolean myFamiliarPresence = false;
        TowerSpace[] towerSpaces = this.getSpaces();
        if (familyMember.getFamilyColor() != FamilyColor.NEUTRAL) {
            for (int i = 0; i < towerSpaces.length; i++) {
                if (towerSpaces[i].getFamilyMember()!= null && towerSpaces[i].getFamilyMember().getPlayer().isEquals(familyMember.getPlayer())) {
                    if (towerSpaces[i].getFamilyMember().getFamilyColor() != FamilyColor.NEUTRAL) {
                        myFamiliarPresence = true;
                    }
                }
            }
        }
        return myFamiliarPresence;
    }

    // controlla se è presente un familiare nella torre
    public boolean checkOtherFamiliar() {
        boolean otherFamiliarPresence  = false;
        TowerSpace[] towerSpaces = this.getSpaces();
        for (int i = 0; i < towerSpaces.length ; i++) {
            if (towerSpaces[i].getFamilyMember() != null) {
                otherFamiliarPresence = true;
            }
        }
        return otherFamiliarPresence;
    }

    public TowerSpace chooseTowerSpace(Player player) throws RemoteException {
        player.getUserHandler().messageToUser("Choose the tower space");
        //todo: mostrare i towerspace
        player.getUserHandler().messageToUser("write");
        String input = player.getUserHandler().messageFromUser();
        int i;
        try {
            i = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            player.getUserHandler().messageToUser("Invalid format");
            return chooseTowerSpace(player);
        }

        try {
            player.getUserHandler().messageToUser("You have chosen: " + i);
            return this.spaces[i];

        } catch (NullPointerException e) {
            player.getUserHandler().messageToUser("Number out of bound, try again");
            return chooseTowerSpace(player);
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(TowerSpace t : spaces){
            stringBuilder.append(t.toString() + "\n" + "_______________________________________________________" +
                    "____________________________________________________________" + "\n");

        }
        return String.valueOf(stringBuilder);
    }

    public void activePermanetEffect(FamilyMember familyMember) throws RemoteException {
        ArrayList<PermanentEffect> permanentEffects = familyMember.getPlayer().getPermanentEffects();
        for (int i = 0; i < permanentEffects.size(); i++) {
            if (permanentEffects.get(i) instanceof PlusDiceEffect && ((PlusDiceEffect) permanentEffects.get(i)).getType().equals("tower")) {
                if (((PlusDiceEffect) permanentEffects.get(i)).getCardColor() == towerColor) {
                    int plusDice = ((PlusDiceEffect) permanentEffects.get(i)).getPlusDiceValue();
                    familyMember.setValue(familyMember.getValue() + plusDice);
                    familyMember.getPlayer().getUserHandler().messageToUser("Your family member value is increased to: " +familyMember.getValue());
                }
            }
        }
    }

    public CardColor getTowerColor() {
        return towerColor;
    }
}

