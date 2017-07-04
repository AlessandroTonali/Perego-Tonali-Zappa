package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.LeaderCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class ActiveLeaderCard implements Controller {

    LeaderCard leaderCard;
    Player player;

    public ActiveLeaderCard(LeaderCard leaderCard, Player player) throws IOException {
        this.leaderCard = leaderCard;
        this.player = player;

        if (isLegal()) {
            makeAction();
            player.chooseMove(player.getView());
        } else {
            player.getUserHandler().messageToUser("YOU CAN'T ACTIVE THE LEADER CARD");
            player.chooseMove(player.getView());
        }
    }

    private boolean isActivable(){
        //TODO
        return true;
    }

    @Override
    public boolean isLegal() {
        boolean legal = true;
        legal = legal && leaderCard.getRequirement().checkRequirement(player);

        legal = legal && !leaderCard.isActivatedInThisTurn();

        return legal;
    }

    @Override
    public void makeAction() throws IOException {
        ArrayList<AbsEffect> leaderCardEffects = leaderCard.getEffect();
        for (int i = 0; i < leaderCardEffects.size(); i++) {
            AbsEffect effect = leaderCardEffects.get(i);
            if (effect instanceof PermanentEffect) {
                player.getPermanentEffects().add((PermanentEffect) effect);
            } else {
                effect.activeEffect(player);
            }
        }
    }
}
