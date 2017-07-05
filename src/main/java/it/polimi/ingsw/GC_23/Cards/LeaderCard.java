package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Requirement;

import java.util.ArrayList;

/**
 * Created by Alessandro on 19/06/2017.
 */
public class LeaderCard {

    private String name;
    private Requirement requirement;
    private ArrayList<AbsEffect> effect;
    private boolean activatedInThisTurn = false;
    private boolean activatedPermanentEffect = false;

    public LeaderCard(String name, Requirement requirement, ArrayList<AbsEffect> effect) {
        this.name = name;
        this.requirement = requirement;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public ArrayList<AbsEffect> getEffect() {
        return effect;
    }

    public boolean isActivatedInThisTurn() {
        return activatedInThisTurn;
    }

    public void setActivatedInThisTurn(boolean activatedInThisTurn) {
        this.activatedInThisTurn = activatedInThisTurn;
    }

    public boolean isActivatedPermanentEffect() {
        return activatedPermanentEffect;
    }

    public void setActivatedPermanentEffect(boolean activatedPermanentEffect) {
        this.activatedPermanentEffect = activatedPermanentEffect;
    }

    @Override
    public String toString() {
        return name;
    }
}
