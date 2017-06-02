package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.DiceColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;

/**
 * Created by jesss on 31/05/17.
 */
public class Board {
    private static Board board;
    private static int towerDim =4;
    private static int marketDim =4;
    private static Tower[] towers;
    private static MarketSpace[] marketSpaces;
    private static CouncilSpace councilSpace;
    private static ProductionSpace productionSpace;
    private static HarvestSpace harvestSpace;
    private static DiceSpace diceSpace;
    private static Dice diceB;
    private static Dice diceO;
    private static Dice diceW;

    private Board(){



        this.towers = towerInstancer();

        this.marketSpaces = new MarketSpace[marketDim];
        this.marketSpaces[0]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(0));
        this.marketSpaces[1]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(1));
        this.marketSpaces[2]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(2));
        this.marketSpaces[3]= new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(3));

        this.councilSpace = new CouncilSpace(ParseJson.getParseJson().getCouncilSpaceEffect());
        this.productionSpace = new ProductionSpace();
        this.harvestSpace = new HarvestSpace();

        this.diceSpace = new DiceSpace();
        setDices();

    }

    private void createTowerSpace() {
    }

    public static void setDices(){
        diceB = new Dice(DiceColor.BLACK);
        diceO = new Dice(DiceColor.ORANGE);
        diceW = new Dice(DiceColor.WHITE);
        diceSpace.setDice(diceB);
        diceSpace.setDice(diceO);
        diceSpace.setDice(diceW);
    }

    public static void setTowers(Tower[] towers) {
        Board.towers = towers;
    }

    synchronized static Board getBoard(){
        if(board==null){
            board = new Board();
        }
        return board;
    }

    public static Tower chooseTower(Player player) {
        System.out.println("choose ur tower");
        String input = player.getNextLine();
        int i;

        try {
            i = Integer.parseInt(input);
            System.out.println("ciao");
            System.out.println(i);

        } catch (NumberFormatException e) {
            System.out.println("unvalid format try again");
            return chooseTower(player);
        }

        try{
            return towers[i];
        }catch (NullPointerException e) {
            System.out.println("number out of bound, insert again");
            return chooseTower(player);
        }
    }

    public static Tower getTower(int i){
        return towers[i];
    }

    public static Tower[] getTowers(){
        return towers;
    }

    public static CouncilSpace getCouncilSpace() {
        return councilSpace;
    }

    public static void resetCardTowers(){
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

    public static MarketSpace[] getMarketSpaces() {
        return marketSpaces;
    }

    public static ProductionSpace getProductionSpace() {
        return productionSpace;
    }

    public static HarvestSpace getHarvestSpace() {
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

        Tower terrytoryTower = new Tower(terrytorySpaces);
        Tower buildingTower = new Tower(buildingSpaces);
        Tower ventureTower = new Tower(ventureSpaces);
        Tower characterTower = new Tower(charchterSpaces);

        Tower[] towers = new Tower[4];

        towers[0] = terrytoryTower;
        towers[1] = characterTower;
        towers[2] = buildingTower;
        towers[3] = ventureTower;

        return towers;

    }








    }
}
