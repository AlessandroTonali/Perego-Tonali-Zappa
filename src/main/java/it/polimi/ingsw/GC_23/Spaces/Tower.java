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
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;
import it.polimi.ingsw.GC_23.StringTyper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Tower {
    private int DIM=3;
    private TowerSpace[] spaces = new TowerSpace[DIM];
    private CardColor towerColor;
    private SingleCost sale;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void setTowerColor(CardColor towerColor) {
        this.towerColor = towerColor;
    }

    public SingleCost getSale() {
        return sale;
    }

    public void setSale(SingleCost sale) {
        this.sale = sale;
    }

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
        sale = new SingleCost(new ResourcesSet(0, 0, 0, 0, 0, 0, 0));
    }


    /**
     * check if present another family member with the same color of the player
     * @param familyMember that want tu put in a tower
     * @return true if the player have already put a family member in this tower,
     * false if the there isn't a player's family member in the tower
     */
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

    /**
     * check if present a family member in this tower
     * @return true if there is a family member,
     * false if there isn't a family member
     */
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

    /**
     * method for choosing a tower space in the tower
     * @param player that want to play in the tower
     * @return the tower space chosen
     * @throws RemoteException
     */
    public TowerSpace chooseTowerSpace(Player player) throws RemoteException {
        if(!player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("Choose the tower space");
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper  = new StringTyper(player);
        executorService.submit(stringTyper);
        while(!player.isTyped() && !player.isTimeIsOver()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
        }
        int i = -1;
        if(player.isTimeIsOver()){
            i = 0;
        }
        if(player.isTyped()){
            player.setTyped(false);
            i = player.getTypedInt();
        }



        try {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("You have chosen: " + i);
            }
            return this.spaces[i];

        } catch (NullPointerException e) {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Number out of bound, try again");
            }
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

    /**
     * check if is possibile to active permanent effect on the tower
     * @param familyMember that you want to put in the tower
     * @return family member with permanent effect activated
     * @throws RemoteException
     */
    public FamilyMember checkPermanentEffect(FamilyMember familyMember) throws RemoteException {
        ArrayList<PermanentEffect> permanentEffects = familyMember.getPlayer().getPermanentEffects();
        for (int i = 0; i < permanentEffects.size(); i++) {
            if (permanentEffects.get(i) instanceof PlusDiceEffect && ((PlusDiceEffect) permanentEffects.get(i)).getType().equals("tower")) {
                if (((PlusDiceEffect) permanentEffects.get(i)).getCardColor() == towerColor) {
                    int plusDice = ((PlusDiceEffect) permanentEffects.get(i)).getPlusDiceValue();
                    familyMember.setValue(familyMember.getValue() + plusDice);
                    //SingleCost sale = ((PlusDiceEffect) permanentEffects.get(i)).chooseSale(familyMember.getPlayer());
                    if (sale !=  null) {
                        this.setSale(((PlusDiceEffect) permanentEffects.get(i)).chooseSale(familyMember.getPlayer()));
                    }
                    familyMember.getPlayer().getUserHandler().messageToUser("Your family member value is increased to: " +familyMember.getValue());
                }
            }
        }

        return familyMember;
    }

    /**
     * method for disable the permanent effect activated earlier when you entered in the tower
     * @param familyMember that you want to disable the permanent effect
     * @return the family member with disabled the permanent effect
     * @throws RemoteException
     */
    public FamilyMember disablePermanentEffect(FamilyMember familyMember) throws RemoteException {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();
        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof PlusDiceEffect && ((PlusDiceEffect) permanentEffect).getType().equals("tower")) {
                int plusDice = ((PlusDiceEffect) permanentEffect).getPlusDiceValue();
                familyMember.setValue(familyMember.getValue() - plusDice);
                this.setSale(new SingleCost(new ResourcesSet(0,0,0,0,0,0,0)));
                familyMember.getPlayer().getUserHandler().messageToUser("Disable permanent effect");
            }
        }

        return familyMember;
    }

    public CardColor getTowerColor() {
        return towerColor;
    }
}

