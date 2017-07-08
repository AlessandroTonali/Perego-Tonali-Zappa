package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.LeaderCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.Effects.SetDiceEffect;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.PlayerTimeOut;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class ActiveLeaderCard implements Controller {

    LeaderCard leaderCard;
    Player player;

    public ActiveLeaderCard(LeaderCard leaderCard, Player player, PlayerTimeOut playerTimeOut) throws IOException {
        this.leaderCard = leaderCard;
        this.player = player;

        if (isLegal()) {
            makeAction();
            player.setTimeIsOver(false);
            playerTimeOut.setNeeded(false);
            player.getUserHandler().messageToUser("YOU HAVE ACTIVATED THE LEADER CARD");
            player.chooseMove(player.getView(), true);
        } else {
            player.getUserHandler().messageToUser("YOU CAN'T ACTIVE THE LEADER CARD");
            playerTimeOut.setNeeded(false);
            player.chooseMove(player.getView(), true);
        }
    }

    @Override
    public boolean isLegal() {
        boolean legal = true;
        legal = legal && leaderCard.getRequirement().checkRequirement(player);

        legal = legal && !leaderCard.isActivatedInThisRound();

        return legal;
    }

    @Override
    public void makeAction() throws IOException {
        ArrayList<AbsEffect> leaderCardEffects = leaderCard.getEffect();
        for (int i = 0; i < leaderCardEffects.size(); i++) {
            AbsEffect effect = leaderCardEffects.get(i);
            if (effect instanceof PermanentEffect) {
                if (leaderCard.isActivatedPermanentEffect())
                    player.getUserHandler().messageToUser("Permanent effect of card is already active");
                else {
                    if ((effect instanceof PlusDiceEffect) || (effect instanceof SetDiceEffect)) {
                        effect.activeEffect(player);
                    }
                    player.getUserHandler().messageToUser("Leader card activeted correctly");
                    player.getPermanentEffects().add((PermanentEffect) effect);
                    leaderCard.setActivatedPermanentEffect(true);
                    leaderCard.setActivatedInThisRound(true);
                }
            } else {
                player.getUserHandler().messageToUser("Leader card activeted correctly");
                effect.activeEffect(player);
                leaderCard.setActivatedInThisRound(true);
            }
        }
    }
}
