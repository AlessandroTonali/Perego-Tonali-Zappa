package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.DiceColor;
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

    private Board(){
        this.towers = new Tower[towerDim];

        this.marketSpaces = new MarketSpace[marketDim];
        this.marketSpaces[0]= new MarketSpace();
        this.marketSpaces[1]= new MarketSpace();
        this.marketSpaces[2]= new MarketSpace();
        this.marketSpaces[3]= new MarketSpace();

        this.councilSpace = new CouncilSpace();
        this.productionSpace = new ProductionSpace();
        this.harvestSpace = new HarvestSpace();

        this.diceSpace = new DiceSpace();
        Dice diceB = new Dice(DiceColor.BLACK);
        Dice diceO = new Dice(DiceColor.ORANGE);
        Dice diceW = new Dice(DiceColor.WHITE);
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

    public static void resetTowers(){
        for( Tower t: getTowers()){
            TowerSpace[] spaces = t.getSpaces();
            for(int i=0; i< towers.length; i++){
                spaces[i].resetCard();
            }
        }
    }
}
