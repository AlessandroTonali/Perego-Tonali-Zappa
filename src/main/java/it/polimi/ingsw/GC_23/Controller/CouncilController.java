package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Effects.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.CouncilSpace;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class CouncilController extends PlaceFamilyMember {
    CouncilSpace councilSpace;
    FamilyMember familyMember;

    public CouncilController(CouncilSpace councilSpace, FamilyMember familyMember) throws IOException {
        this.councilSpace = councilSpace;
        this.familyMember = familyMember;
        if(isLegal()){
            makeAction();
        }
        else {
            familyMember.getPlayer().getUserHandler().messageToUser("YOU ARE NOT ALLOW TO DO THIS MOVE, DO SOMETHING ELSE!");
            throw new IllegalArgumentException();
        }
    }
    @Override
    public boolean isLegal(){
        if(familyMember.getValue()>=1 && !councilSpace.checkFamiliar(familyMember.getPlayer().getPlayerColor())){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void makeAction() throws IOException {
        Player player = familyMember.getPlayer();
        councilSpace.setFamilyMember(familyMember);
        ArrayList<AbsEffect> effects = councilSpace.getEffects();
        for(AbsEffect i : effects) {
            i.activeEffect(familyMember.getPlayer());
        }
        player.getUserHandler().messageToUser(familyMember.getPlayer().getResources().toString());
    }
}


