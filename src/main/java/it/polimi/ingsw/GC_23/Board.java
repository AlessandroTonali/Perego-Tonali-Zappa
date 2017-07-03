package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.DiceColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 31/05/17.
 */
public class Board {
    private int towerDim =4;
    private Tower[] towers;
    private MarketSpace[] marketSpaces;
    private CouncilSpace councilSpace;
    private ProductionSpace productionSpace;
    private HarvestSpace harvestSpace;
    private DiceSpace diceSpace;
    private ExcommunicationSpace excommunicationSpaceFirstPeriod;
    private ExcommunicationSpace excommunicationSpaceSecondPeriod;
    private ExcommunicationSpace excommunicationSpaceThirdPeriod;
    private Dice diceB;
    private Dice diceO;
    private Dice diceW;
    private ArrayList<TerritoryCard> territoryCards;
    private ArrayList<VentureCard> ventureCards;
    private ArrayList<BuildingCard> buildingCards;
    private ArrayList<CharacterCard> characterCards;

    public Board(int numberOfPlayers){
        this.towers = towerInstancer();
        this.territoryCards = ParseJson.getParseJson().getTerritoryCardArrayList();
        this.ventureCards = ParseJson.getParseJson().getVentureCardArrayList();
        this.buildingCards = ParseJson.getParseJson().getBuildingCardArrayList();
        this.characterCards = ParseJson.getParseJson().getCharacterCardArrayList();
        setCard();
        switch (numberOfPlayers){
            case 2:
                this.productionSpace = new ProductionSpace(false);
                this.harvestSpace = new HarvestSpace(false);
                this.marketSpaces = new MarketSpace[2];
                this.marketSpaces[0]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(0));
                this.marketSpaces[1]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(1));
                break;
            case 3:
                this.productionSpace = new ProductionSpace(true);
                this.harvestSpace = new HarvestSpace(true);
                this.marketSpaces = new MarketSpace[2];
                this.marketSpaces[0]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(0));
                this.marketSpaces[1]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(1));
                break;
            case 4:
                this.productionSpace = new ProductionSpace(true);
                this.harvestSpace = new HarvestSpace(true);
                this.marketSpaces = new MarketSpace[4];
                this.marketSpaces[0]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(0));
                this.marketSpaces[1]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(1));
                this.marketSpaces[2]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(2));
                this.marketSpaces[3]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(3));
                break;
            default:
                System.out.println("wrong number of players");
                break;
        }

        this.councilSpace = new CouncilSpace(ParseJson.getParseJson().getCouncilSpaceEffect());

        this.diceSpace = new DiceSpace();
        setDices();

        excommunicationSpaceFirstPeriod = new ExcommunicationSpace(ParseJson.getParseJson().getExcommunicationTileFirstPeriod());
        excommunicationSpaceSecondPeriod = new ExcommunicationSpace(ParseJson.getParseJson().getExcommunicationTileSecondPeriod());
        excommunicationSpaceThirdPeriod = new ExcommunicationSpace(ParseJson.getParseJson().getExcommunicationTileThirdPeriod());

    }

    public void setCardInTowerSpace(int i, Tower tower ) {
        int j = 0;
        switch (i) {
            case  0:
                for (TowerSpace ts : tower.getSpaces()) {

                    ts.setCard(territoryCards.get(j));
                    territoryCards.remove(0);
                    j++;

                } break;
            case 1:
                for (TowerSpace ts : tower.getSpaces()) {

                    ts.setCard(characterCards.get(j));
                    characterCards.remove(0);
                    j++;

                } break;

            case 2:
                for (TowerSpace ts : tower.getSpaces()) {

                    ts.setCard(buildingCards.get(j));
                    buildingCards.remove(0);
                    j++;

                } break;
            case 3:
                for (TowerSpace ts : tower.getSpaces()) {

                    ts.setCard(ventureCards.get(j));
                    ventureCards.remove(0);
                    j++;

                } break;
            default: throw new UnsupportedOperationException();

        }



    }

    public void setCard() {
        int i = 0;
        for (Tower t : towers) {
            setCardInTowerSpace(i,t);
            i++;


        }
    }

    public void setDices(){
        diceB = new Dice(DiceColor.BLACK);
        diceO = new Dice(DiceColor.ORANGE);
        diceW = new Dice(DiceColor.WHITE);
        diceSpace.setDice(diceB);
        diceSpace.setDice(diceO);
        diceSpace.setDice(diceW);
    }

    public void setTowers(Tower[] towers) {
        this.towers = towers;
    }

    public Tower chooseTower(Player player) throws RemoteException {
        player.getUserHandler().messageToUser("Choose a tower");
        player.getUserHandler().messageToUser("write");
        String input = player.getUserHandler().messageFromUser();
        int i;

        try {
            i = Integer.parseInt(input);
            player.getUserHandler().messageToUser("Chosen tower");
        } catch (NumberFormatException e) {
            player.getUserHandler().messageToUser("Invalid format, try again");
            return chooseTower(player);
        }

        try{
            return towers[i];
        }catch (NullPointerException e) {
            player.getUserHandler().messageToUser("Number out of bound, insert again");
            return chooseTower(player);
        }
    }

    public Tower getTower(int i){
        return towers[i];
    }

    public Tower[] getTowers(){
        return towers;
    }

    public CouncilSpace getCouncilSpace() {
        return councilSpace;
    }

    public void resetCardTowers(){
        for( Tower t: getTowers()){
            TowerSpace[] spaces = t.getSpaces();
            for(int i=0; i< towers.length; i++){
                spaces[i].resetCard();
            }
        }
    }

    public int getDiceBValue(){
        return diceB.getValue();
    }

    public int getDiceOValue(){
        return diceO.getValue();
    }

    public int getDiceWValue(){
        return diceW.getValue();
    }

    public MarketSpace[] getMarketSpaces() {
        return marketSpaces;
    }

    public ProductionSpace getProductionSpace() {
        return productionSpace;
    }

    public HarvestSpace getHarvestSpace() {
        return harvestSpace;
    }

    private Tower[] towerInstancer() {
        TowerSpace terrytory1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[0],
                0);
        TowerSpace terrytory2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[1],
                3);
        TowerSpace terrytory3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[2],
                5);
        TowerSpace terrytory4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[3],
                7);
        TowerSpace[] terrytorySpaces = new TowerSpace[4];

        terrytorySpaces[0] = terrytory1;
        terrytorySpaces[1] = terrytory2;
        terrytorySpaces[2] = terrytory3;
        terrytorySpaces[3] = terrytory4;

        TowerSpace character1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerCharacterEffect()[0], 0);
        TowerSpace character2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerCharacterEffect()[1], 3);
        TowerSpace character3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerCharacterEffect()[2], 5);
        TowerSpace character4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerCharacterEffect()[3], 7);

        TowerSpace[] charchterSpaces = new TowerSpace[4];

        charchterSpaces[0] = character1;
        charchterSpaces[1] = character2;
        charchterSpaces[2] = character3;
        charchterSpaces[3] = character4;

        TowerSpace venture1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerVentureEffect()[0], 0);
        TowerSpace venture2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerVentureEffect()[1], 3);
        TowerSpace venture3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerVentureEffect()[2], 5);
        TowerSpace venture4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerVentureEffect()[3], 7);

        TowerSpace[] ventureSpaces = new TowerSpace[4];

        ventureSpaces[0] = venture1;
        ventureSpaces[1] = venture2;
        ventureSpaces[2] = venture3;
        ventureSpaces[3] = venture4;

        TowerSpace building1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerBuildingEffect()[0], 0);
        TowerSpace building2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerBuildingEffect()[1], 3);
        TowerSpace building3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerBuildingEffect()[2], 5);
        TowerSpace building4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson()
                .getTowerBuildingEffect()[3], 7);
        TowerSpace[] buildingSpaces = new TowerSpace[4];
        buildingSpaces[0] = building1;
        buildingSpaces[1] = building2;
        buildingSpaces[2] = building3;
        buildingSpaces[3] = building4;

        Tower terrytoryTower = new Tower(terrytorySpaces, CardColor.GREEN);
        Tower buildingTower = new Tower(buildingSpaces, CardColor.YELLOW);
        Tower ventureTower = new Tower(ventureSpaces, CardColor.PURPLE);
        Tower characterTower = new Tower(charchterSpaces, CardColor.BLUE);

        Tower[] towers = new Tower[4];

        towers[0] = terrytoryTower;
        towers[1] = characterTower;
        towers[2] = buildingTower;
        towers[3] = ventureTower;

        return towers;

    }

    public String towersStringer() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for(Tower t : towers) {
            switch (i){
                case 0: stringBuilder.append("TERRITORY TOWER\n"); break;
                case 1: stringBuilder.append("CHARACTER TOWER\n"); break;
                case 2: stringBuilder.append("BUILDING TOWER\n"); break;
                case 3: stringBuilder.append("VENTURE TOWER\n"); break;
            }
             stringBuilder.append(t.toString());
            i++;
        }
        return String.valueOf(stringBuilder);
    }

    public String marketStringer() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MarketSpace m : marketSpaces) {
            stringBuilder.append(m.toString() + "\n");
        }
        return String.valueOf(stringBuilder);
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TOWERS\n");
        stringBuilder.append(towersStringer() + "\n");
        stringBuilder.append("MARKET\n");
        stringBuilder.append(marketStringer());
        stringBuilder.append("HARVEST SPACE\n");
        stringBuilder.append(harvestSpace.toString());
        stringBuilder.append("PRODUCTION SPACE\n");
        stringBuilder.append(productionSpace.toString());
        stringBuilder.append("COUNCIL SPACE\n");
        stringBuilder.append(councilSpace.toString()+"\n");
        stringBuilder.append("DICES\n");
        stringBuilder.append(diceSpace.toString());

        return String.valueOf(stringBuilder);

    }

    public ExcommunicationSpace getExcommunicationSpaceFirstPeriod() {
        return excommunicationSpaceFirstPeriod;
    }

    public ExcommunicationSpace getExcommunicationSpaceSecondPeriod() {
        return excommunicationSpaceSecondPeriod;
    }

    public ExcommunicationSpace getExcommunicationSpaceThirdPeriod() {
        return excommunicationSpaceThirdPeriod;
    }
}

