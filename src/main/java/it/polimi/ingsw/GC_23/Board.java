package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Spaces.CouncilSpace;

/**
 * Created by jesss on 31/05/17.
 */
public class Board {
    private static Board board;
    private Board(){}
    synchronized static Board getBoard(){
        if(board==null){
            board = new Board();
        }
        return board;
    }
}
