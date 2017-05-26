package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;

import java.util.ArrayList;

/**
 * Created by Alessandro on 21/05/2017.
 */
public class CardOfPlayer {


    private ArrayList<TerritoryCard> territoryCards;
    private ArrayList<CharacterCard> characterCards;
    private ArrayList<BuildingCard> buildingCards;
    private ArrayList<VentureCard> ventureCards;

    public ArrayList<TerritoryCard> getTerritoryCards() {
        return territoryCards;
    }

    public ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }

    public ArrayList<BuildingCard> getBuildingCards() {
        return buildingCards;
    }

    public ArrayList<VentureCard> getVentureCards() {
        return ventureCards;
    }

}
