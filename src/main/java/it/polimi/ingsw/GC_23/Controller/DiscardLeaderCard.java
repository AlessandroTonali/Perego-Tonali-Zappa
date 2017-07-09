package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.LeaderCard;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.PlayerTimeOut;
import sun.security.krb5.internal.PAData;

import java.io.IOException;

/**
 * Created by jesss on 23/05/17.
 */
public class DiscardLeaderCard implements Controller {

    private LeaderCard leaderCard;
    private Player player;

    public DiscardLeaderCard(LeaderCard leaderCard, Player player, PlayerTimeOut playerTimeOut) throws IOException {
        this.leaderCard = leaderCard;
        this.player = player;

        if (isLegal()) {
            makeAction();
            player.setTimeIsOver(false);
            playerTimeOut.setNeeded(false);
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("YOU HAVE DISCARDED THE LEADER CARD");
            }
            player.chooseMove(player.getView(), true);
        } else {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("YOU CAN'T DISCARD THE LEADER CARD");
            }
            playerTimeOut.setNeeded(false);
            player.chooseMove(player.getView(), true);
        }
    }

    @Override
    public boolean isLegal() {
        boolean legal = true;

        legal = legal && !leaderCard.isDiscardedInThisTurn();

        return legal;
    }

    @Override
    public void makeAction() throws IOException {
        if(player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("effect");
        }
        ParseJson.getParseJson().getEffectMap().get(1).activeEffect(player);
        if(player.getUserHandler().isGuiInterface()){
            player.getUserHandler().messageToUser("effectended");
        }
        leaderCard.setDiscardedInThisTurn(true);
    }
}
