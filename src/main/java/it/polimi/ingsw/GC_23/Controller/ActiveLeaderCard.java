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

    /**
     *   active the the effect of leader card
     * @param leaderCard that you want to active
     * @param player that use the leader card
     * @param playerTimeOut the time out of the game
     * @throws IOException
     */
    public ActiveLeaderCard(LeaderCard leaderCard, Player player, PlayerTimeOut playerTimeOut) throws IOException {
        this.leaderCard = leaderCard;
        this.player = player;

        if (isLegal()) {
            makeAction();
            player.setTimeIsOver(false);
            playerTimeOut.setNeeded(false);
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("YOU HAVE ACTIVATED THE LEADER CARD");
            }
            player.getUserHandler().messageToUser("YOU HAVE ACTIVATED THE LEADER CARD");
        } else {
            if(!player.getUserHandler().isGuiInterface()){
                player.getUserHandler().messageToUser("YOU CAN'T ACTIVE THE LEADER CARD");
            }
            playerTimeOut.setNeeded(false);
        }
    }

    /**
     * show on interface
     * @return
     */
    @Override
    public boolean isLegal() {
        boolean legal = false;
        if (leaderCard.getRequirement().checkRequirement(player)){
            legal = true;
        }
        if (leaderCard.isActivatedInThisRound()){
            legal = false;
        }
        return legal;
    }

    /**
     * show on interface
     * @throws IOException
     */
    @Override
    public void makeAction() throws IOException {
        ArrayList<AbsEffect> leaderCardEffects = leaderCard.getEffect();
        for (int i = 0; i < leaderCardEffects.size(); i++) {
            AbsEffect effect = leaderCardEffects.get(i);
            if (effect instanceof PermanentEffect) {
                if (leaderCard.isActivatedPermanentEffect() && !player.getUserHandler().isGuiInterface())
                    player.getUserHandler().messageToUser("Permanent effect of card is already active");
                else {
                    if (((effect instanceof PlusDiceEffect) || (effect instanceof SetDiceEffect))) {
                        if(player.getUserHandler().isGuiInterface()) {
                            player.getUserHandler().messageToUser("effect");
                        }
                        effect.activeEffect(player);
                        if(player.getUserHandler().isGuiInterface()){
                            player.getUserHandler().messageToUser("effectended");
                        }
                    }
                    if(!player.getUserHandler().isGuiInterface()) {
                        player.getUserHandler().messageToUser("Leader card activeted correctly");
                    }
                    player.getPermanentEffects().add((PermanentEffect) effect);
                    leaderCard.setActivatedPermanentEffect(true);
                    leaderCard.setActivatedInThisRound(true);
                }
            } else {
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("Leader card activeted correctly");
                }
                if(player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("effect");
                }
                effect.activeEffect(player);
                if(player.getUserHandler().isGuiInterface()){
                    player.getUserHandler().messageToUser("effectended");
                }
                leaderCard.setActivatedInThisRound(true);
            }
        }
    }
}
