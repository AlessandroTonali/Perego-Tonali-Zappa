package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;

/**
 * Created by Alessandro on 05/07/2017.
 */
public class FlagEffect extends AbsEffect {

    private String type;

    /**
     * effect that set a parameter on player
     * @param type
     */
    public FlagEffect(String type) {
        this.type = type;
    }

    /**
     *
     * @param player that want to active the effect
     * @throws IOException
     */
    @Override
    public void activeEffect(Player player) throws IOException {
        switch (type) {
            case "venture":
                player.setNotScoreVenture(true);
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("Effect activated! At the end of the game you haven't scored the venture card");
                }
                break;
            case "character":
                player.setNotScoreCharacter(true);
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("Effect activated! At the end of the game you haven't scored the character card");
                }
                break;
            case "territory":
                player.setNotScoreTerrytory(true);
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("Effect activated! At the end of the game you haven't scored the territory card");
                }
                break;
            case "market":
                player.setNotPlayInMarket(true);
                if(!player.getUserHandler().isGuiInterface()) {
                    player.getUserHandler().messageToUser("Effect activated! Now you cannot use the market");
                }
                break;
            case "double_servant":
                player.setDoubleServantToIncrease(true);
                break;
            case "military_territory":
                player.setNotCheckMilitaryOnTerritory(true);
                break;
            case "point_vatican":
                player.setPointOnVatican(true);
                break;
            case "spend_tower":
                player.setNotSpendOnOccupiedTower(true);
                break;

        }
    }
}
