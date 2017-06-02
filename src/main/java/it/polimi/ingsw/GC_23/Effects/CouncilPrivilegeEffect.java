package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jesss on 21/05/17.
 */
public class CouncilPrivilegeEffect extends AbsEffect{
    //pergamena a scelta tra: 1 wood + 1 stone, 2 servants, 2 golds, 2 military, 1 faith
    private BenefitsEffect[] benefits;
    private int numberOfPrivileges;
    boolean isDifferent;
    private FamilyMember familyMember;


    public CouncilPrivilegeEffect(int numberOfPrivileges, boolean isDifferent) {
        this.numberOfPrivileges = numberOfPrivileges;
        this.isDifferent = isDifferent;
        this.benefits = ParseJson.getParseJson().getCouncilBenefit();
    }

    public void setBenefits(BenefitsEffect[] benefits) {
        this.benefits = benefits;
    }

    public void setNumberOfPrivileges(int numberOfPrivileges) {
        this.numberOfPrivileges = numberOfPrivileges;
    }

    public int getNumberOfPrivileges() {
        return numberOfPrivileges;
    }

    public BenefitsEffect[] getBenefits() {
        return benefits;
    }

    public BenefitsEffect[] chooseCouncilPrivilege(Player player) {
        int i;
        ArrayList<Integer> chosenEffects = new ArrayList<Integer>();
        int l = 0;
        String string;
        BenefitsEffect[] chosen = new BenefitsEffect[this.getNumberOfPrivileges()];
        while (this.getNumberOfPrivileges() > 0) {
            System.out.println("Select possible council privilege:");
            for (int n = 0; n < benefits.length; n++) {
                System.out.println(n + ": " + benefits[n].toString());
            }
            if (!isDifferent) {
                try {
                    string = player.getNextLine();
                    i = Integer.parseInt(string);
                    if(i<benefits.length){
                        System.out.println("Chosen council privilege");
                    }
                    else{
                        System.out.println("Error: incorrect number, try again");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid council privilege, please try again");
                    continue;
                }
                try {
                    chosen[l] = this.benefits[i];
                    l++;
                    System.out.println("You get: " + this.benefits[i].getResources().toString());
                    System.out.println();
                    this.setNumberOfPrivileges(this.getNumberOfPrivileges() - 1);
                } catch (NullPointerException ex) {
                    return null;
                }
            } else {
                try {
                    string = player.getNextLine();
                    i = Integer.parseInt(string);
                    if(i<benefits.length){
                        System.out.println("Chosen council privilege");
                    }
                    else{
                        System.out.println("Error: incorrect number, try again");
                        continue;
                    }
                    if(!alreadyTaken(chosenEffects, i)) {
                        chosenEffects.add(i);
                    }else{
                        System.out.println("already taken, please choose another one");
                        continue;
                    }
                    System.out.println("Chosen different council privilege");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid council privilege, please try again");
                    continue;
                }
                try{
                    chosen[l] = this.benefits[i];
                    l++;
                    System.out.println("You get:  " + this.benefits[i].getResources().toString());
                    System.out.println();
                    this.setNumberOfPrivileges(this.getNumberOfPrivileges() - 1);
                } catch (NullPointerException ex) {
                    return null;
                }
            }
        }
        System.out.println("You have chosen all your council privilege");
        return chosen;
    }


    public void activeEffect(Player player) {
        BenefitsEffect[] chosenEffect = chooseCouncilPrivilege(player);
        for(BenefitsEffect e: chosenEffect){
            e.activeEffect(player);
        }

    }

    @Override
    public int getTyteEffect() {
        return EffectType.COUNCIL_EFFECT_TYPE;
    }

    public boolean alreadyTaken(ArrayList<Integer> checkedList, int checkedNumber){
        for(int i : checkedList) {
            if (i == checkedNumber) {
                return true;
            }
        }
            return false;
    }

}


