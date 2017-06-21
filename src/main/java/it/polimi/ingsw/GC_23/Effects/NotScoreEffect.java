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
                break;
            case "character":
                player.setNotScoreCharacter(true);
                break;
            case "territory":
                player.setNotScoreTerrytory(true);
                break;
        }
    }
}
