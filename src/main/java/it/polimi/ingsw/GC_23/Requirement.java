package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

/**
 * Created by Alessandro on 03/07/2017.
 */
public class Requirement {
    private int numberVenture;
    private int numberCharacter;
    private int numberBuilding;
    private int numberTerritory;
    private ResourcesSet resources;

    public Requirement(int numberVenture, int numberCharacter, int numberBuilding, int numberTerritory, ResourcesSet resources) {
        this.numberVenture = numberVenture;
        this.numberCharacter = numberCharacter;
        this.numberBuilding = numberBuilding;
        this.numberTerritory = numberTerritory;
        this.resources = resources;
    }

    /**
     * method for verify if the player satisfy the requirment
     * @param player that want to check the requirment
     * @return true if satisfy the requirment, false if not
     */
    public boolean checkRequirement(Player player) {
        boolean isRequirementSatisfied = true;
        if (player.getCardOfPlayer().getVentureCards().size() < numberVenture) {
            isRequirementSatisfied = false;
        }
        if (player.getCardOfPlayer().getCharacterCards().size() < numberCharacter) {
            isRequirementSatisfied = false;
        }
        if (player.getCardOfPlayer().getBuildingCards().size() < numberBuilding) {
            isRequirementSatisfied = false;
        }
        if (player.getCardOfPlayer().getTerritoryCards().size() < numberTerritory) {
            isRequirementSatisfied = false;
        }
        if (!player.getResources().checkAffordable(resources)) {
            isRequirementSatisfied = false;
        }

        return isRequirementSatisfied;
    }

    public int getNumberVenture() {
        return numberVenture;
    }

    public int getNumberCharacter() {
        return numberCharacter;
    }

    public int getNumberBuilding() {
        return numberBuilding;
    }

    public int getNumberTerritory() {
        return numberTerritory;
    }

    public ResourcesSet getResources() {
        return resources;
    }
}
