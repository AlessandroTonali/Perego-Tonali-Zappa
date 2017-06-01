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
    private Tower[] towers;
    private MarketSpace[] marketSpaces;
    private CouncilSpace councilSpace;
    private ProductionSpace productionSpace;
    private HarvestSpace harvestSpace;
    private DiceSpace diceSpace;

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

    synchronized static Board getBoard(){
        if(board==null){
            board = new Board();
        }
        return board;
    }
}
