package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.StringTyper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 21/05/17.
 */
public class CouncilPrivilegeEffect extends AbsEffect {
    //pergamena a scelta tra: 1 wood + 1 stone, 2 servants, 2 golds, 2 military, 1 faith
    private BenefitsEffect[] benefits;
    private int numberOfPrivileges;
    boolean isDifferent;
    private FamilyMember familyMember;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CouncilPrivilegeEffect(int numberOfPrivileges, boolean isDifferent) {
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

    public BenefitsEffect[] chooseCouncilPrivilege(Player player) throws RemoteException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper = new StringTyper(player);
        ArrayList<Integer> chosenEffects = new ArrayList<Integer>();
        int l = 0;
        String string;
        BenefitsEffect[] chosen = new BenefitsEffect[this.getNumberOfPrivileges()];
        int numBen = this.getNumberOfPrivileges();
        while (numBen > 0) {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Select possible council privilege:");
            }
                for (int n = 0; n < benefits.length; n++) {
                    player.getUserHandler().messageToUser(n + ": " + benefits[n].toString());
                }
            if(player.getUserHandler().isGuiInterface()){
                player.getUserHandler().messageToUser("end");
            }
            if (!isDifferent) {
                int i = -1;
                executorService.submit(stringTyper);
                while (!player.isTimeIsOver() && !player.isTyped()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                        Thread.currentThread().interrupt();
                    }
                }
                if(player.isTimeIsOver()){
                    player.setTimeIsOver(false);
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("read");
                    }
                    return  null;
                }
                if(player.isTyped()){
                    player.setTyped(false);
                    i = player.getTypedInt();
                }

                    if (i < benefits.length) {
                        if(!player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("Chosen council privilege");
                        }
                    } else {
                        if(!player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("Error: incorrect number, try again");
                        }
                        continue;

                }

                try {
                    chosen[l] = this.benefits[i];
                    l++;
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("You get: " + this.benefits[i].getResources().toString());
                    }
                    numBen--;
                } catch (NullPointerException ex) {
                    return null;
                }
            } else {
                    int i = -1;
                    StringTyper stringTyper1 = new StringTyper(player);
                    executorService.submit(stringTyper1);
                    while (!player.isTimeIsOver() && !player.isTyped()){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            logger.setLevel(Level.SEVERE);
                            logger.severe(String.valueOf(e));
                            Thread.currentThread().interrupt();
                        }
                    }
                    if(player.isTimeIsOver()){
                        player.setTimeIsOver(false);
                        if(!player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("read");
                        }
                        return  null;
                    }
                    if(player.isTyped()){
                        player.setTyped(false);
                        i = player.getTypedInt();
                    }
                    if (i < benefits.length) {
                        if(!player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("Chosen council privilege");
                        }
                    } else {
                        if(!player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("Error: incorrect number, try again");
                        }
                        continue;
                    }
                    if (!alreadyTaken(chosenEffects, i)) {
                        chosenEffects.add(i);
                    } else {
                        if(!player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("Already taken, please choose another one");
                        }
                        continue;
                    }
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("Chosen different council privilege");
                    }
                try {
                    chosen[l] = this.benefits[i];
                    l++;
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("You get:  " + this.benefits[i].getResources().toString());
                        player.getUserHandler().messageToUser("");
                    }
                    numBen--;
                } catch (NullPointerException ex) {
                    return null;
                }
            }
        }
        if(player.getUserHandler().isGuiInterface()){
            player.getUserHandler().messageToUser("councilprivilegeend");
        }

        if(!player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("You have chosen all your council privilege");
        }
        return chosen;
    }


    public void activeEffect(Player player) throws RemoteException {
        if(player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("councilEffect");
        }
        BenefitsEffect[] chosenEffect = chooseCouncilPrivilege(player);
        for (BenefitsEffect e : chosenEffect) {
            if(player.getUserHandler().isGuiInterface()){
                player.getUserHandler().messageToUser("effect");
            }

            e.activeEffect(player);
        }
    }

    public boolean alreadyTaken(ArrayList<Integer> checkedList, int checkedNumber) {
        for (int i : checkedList) {
            if (i == checkedNumber) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (isDifferent) {
            return "Number of council privileges: " + numberOfPrivileges + "You have to take different privileges";
        }
        return "Number of council privileges: " + numberOfPrivileges + "You have to take different privileges";

    }
}


