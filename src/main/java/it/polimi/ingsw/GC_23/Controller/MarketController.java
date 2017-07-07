package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;
import it.polimi.ingsw.GC_23.StringTyper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 23/05/17.
 */
public class MarketController extends PlaceFamilyMember {

    private FamilyMember familyMember;
    private MarketSpace[] marketSpace = new MarketSpace[DIM];
    private static int DIM = 4;
    private MarketSpace chosenSpace;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MarketController(FamilyMember familyMember, MarketSpace[] marketSpace) throws IOException {
        this.familyMember = familyMember;
        this.marketSpace = marketSpace;
        familyMember.getPlayer().getUserHandler().messageToUser("Choose the market space");
        int i = 0;
        for(MarketSpace m : marketSpace){
            if (m.getFamilyMember() == null){
                familyMember.getPlayer().getUserHandler().messageToUser("Press " + i + " for getting: " + m.getEffect().toString());
            }
            i++;
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        StringTyper stringTyper = new StringTyper(familyMember.getPlayer());
        executorService.submit(stringTyper);

        int j = -1;
        while (!familyMember.getPlayer().isTimeIsOver() && !familyMember.getPlayer().isTyped()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
            }
        }
        if(familyMember.getPlayer().isTimeIsOver()){
            familyMember.getPlayer().setTimeIsOver(false);
            familyMember.getPlayer().getUserHandler().messageToUser("read");
            return;
        }
        if(familyMember.getPlayer().isTyped()){
            familyMember.getPlayer().setTyped(false);
            j = familyMember.getPlayer().getTypedInt();
        }
        this.chosenSpace = this.marketSpace[j];
        if (isLegal()) {
            makeAction();
        } else {
            familyMember.getPlayer().getUserHandler().messageToUser("YOU ARE NOT ALLOW TO DO THIS MOVE, DO SOMETHING ELSE!");
            throw new IllegalArgumentException();
        }
    }

    public boolean isLegal(){
        if(!(chosenSpace.checkBusy())&&(chosenSpace.checkValue(familyMember)) && !familyMember.getPlayer().isNotPlayInMarket()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void makeAction() throws IOException {
        chosenSpace.getEffect().activeEffect(familyMember.getPlayer());
        chosenSpace.setFamilyMember(familyMember);
    }
}
