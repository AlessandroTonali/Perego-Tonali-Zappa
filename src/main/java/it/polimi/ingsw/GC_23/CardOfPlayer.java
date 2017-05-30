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

    public void setTerritoryCards(ArrayList<TerritoryCard> territoryCards) {
        this.territoryCards = territoryCards;
    }

    public void setCharacterCards(ArrayList<CharacterCard> characterCards) {
        this.characterCards = characterCards;
    }

    public void setBuildingCards(ArrayList<BuildingCard> buildingCards) {
        this.buildingCards = buildingCards;
    }

    public void setVentureCards(ArrayList<VentureCard> ventureCards) {
        this.ventureCards = ventureCards;
    }

    public void setCard(TerritoryCard territoryCard) {
        this.territoryCards.add(territoryCard);
    }

    public void setCard(CharacterCard characterCard) {
        this.characterCards.add(characterCard);
    }

    public void setCard(BuildingCard buildingCard) {
        this.buildingCards.add(buildingCard);
    }

    public void setCard(VentureCard ventureCard) {
        this.ventureCards.add(ventureCard);
    }

    public CardOfPlayer(){
        this.territoryCards = new ArrayList<>(0);
        this.characterCards = new ArrayList<>(0);
        this.buildingCards = new ArrayList<>(0);
        this.ventureCards = new ArrayList<>(0);
    }

    public CardOfPlayer(ArrayList<TerritoryCard> territoryCards, ArrayList<CharacterCard> characterCards, ArrayList<BuildingCard> buildingCards, ArrayList<VentureCard> ventureCards) {
        this.territoryCards = territoryCards;
        this.characterCards = characterCards;
        this.buildingCards = buildingCards;
        this.ventureCards = ventureCards;
    }



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
