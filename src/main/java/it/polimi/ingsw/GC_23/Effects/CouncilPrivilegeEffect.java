package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

import java.util.Scanner;

/**
 * Created by jesss on 21/05/17.
 */
public class CouncilPrivilegeEffect{
    //pergamena a scelta tra: 1 wood + 1 stone, 2 servants, 2 golds, 2 military, 1 faith
    private BenefitsEffect[] benefits;
    private int numberOfPrivileges;
    boolean isDifferent;
    private FamilyMember familyMember;


    public CouncilPrivilegeEffect(BenefitsEffect[] benefits, int numberOfPrivileges, boolean isDifferent) {
        this.benefits = benefits;
        this.numberOfPrivileges = numberOfPrivileges;
        this.isDifferent = isDifferent;
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

    public BenefitsEffect[] chooseCouncilPrivilege(){
        System.out.println("Choose your council privilege");
        BenefitsEffect chosen;
        int i;
        System.out.println("Possibile privilege:");
        if(!isDifferent) {
            for (int n = 0; n < benefits.length; n++) {
                System.out.println(n + ": " + benefits[n].toString());
            }
            Scanner in = new Scanner(System.in);
            String string = in.nextLine();
            try {
                i = Integer.parseInt(string);
                System.out.println("Chosen council privilege");
            } catch (NumberFormatException e) {
                System.out.println("Invalid council privilege");
                return null;
            }
            try{
                chosen = this.benefits[i];
            }catch(NullPointerException ex){
                return null;
            }
            }
        else {
            /*TODO
                for (int n = 0; n < benefits.length; n++) {
                for(int m = 1; m <benefits.length; m++){
                    if(!(benefits[n].equals(benefits[m]))){
                        System.out.println(n + ": " + benefits[n].toString());
            }*/
            Scanner in = new Scanner(System.in);
            String string = in.nextLine();
            try {
                i = Integer.parseInt(string);
                System.out.println("Chosen different council privilege");
            } catch (NumberFormatException e) {
                System.out.println("Invalid council privilege");
                return null;
            }
            try {
                chosen = this.benefits[i];
            } catch (NullPointerException ex) {
                return null;
            }
            return null;
        }
        System.out.println("You get: "+this.benefits[i].getResources().toString());
        return this.benefits;//todo: scelta di più benefits
    }


    public void activeEffect(Player player) {
        BenefitsEffect[] chosenEffect = chooseCouncilPrivilege();
        for(BenefitsEffect e: chosenEffect){
            e.activeEffect(familyMember.getPlayer());
        }

    }

}
