package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Alessandro on 16/06/2017.
 */
public class EndGameEffect extends PermanentEffect {

    private int type;

    public EndGameEffect(int type) {
        this.type = type;
    }

    @Override
    public void activeEffect(Player player) throws IOException {
        switch (type) {
            case 1:
                int playerVictoryPoint = player.getResources().getVictoryPoints();
                int victoryPointLost = playerVictoryPoint % 5;
                playerVictoryPoint = playerVictoryPoint - victoryPointLost;
                player.getResources().setVictoryPoints(playerVictoryPoint);
                break;
            case 2:
                int playerMilitaryPoint = player.getResources().getMilitaryPoints();
                player.getResources().setVictoryPoints(player.getResources().getVictoryPoints() - playerMilitaryPoint);
                break;
            case 3:
                int totStone = 0, totWood = 0;
                ArrayList<BuildingCard> buildingCards = player.getCardOfPlayer().getBuildingCards();
                for (int i = 0; i < buildingCards.size(); i++) {
                    int stone = buildingCards.get(i).getCostSelected().getResources().getStone();
                    int wood = buildingCards.get(i).getCostSelected().getResources().getWood();

                    totStone = totStone + stone;
                    totWood = totWood + wood;
                }
                break;
            case 4:
                //TODO
                break;
        }
    }
}
