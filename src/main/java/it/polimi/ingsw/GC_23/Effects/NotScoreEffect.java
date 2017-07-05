package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;

/**
 * Created by Alessandro on 21/06/2017.
 */
public class NotScoreEffect extends AbsEffect {

    private String notScore;

    public NotScoreEffect(String notScore) {
        this.notScore = notScore;
    }

    @Override
    public void activeEffect(Player player) throws IOException {
        switch (notScore) {
            case "venture":
                player.setNotScoreVenture(true);
                player.getUserHandler().messageToUser("Effect activated! At the end of the game you haven't scored the venture card");
                break;
            case "character":
                player.setNotScoreCharacter(true);
                player.getUserHandler().messageToUser("Effect activated! At the end of the game you haven't scored the character card");
                break;
            case "territory":
                player.setNotScoreTerrytory(true);
                player.getUserHandler().messageToUser("Effect activated! At the end of the game you haven't scored the territory card");
                break;
            case "market":
                player.setNotPlayInMarket(true);
                player.getUserHandler().messageToUser("Effect activated! Now you cannot use the market");
                break;
            case "doubleServant":
                player.setDoubleServantToIncrease(true);
                break;

        }
    }
}
