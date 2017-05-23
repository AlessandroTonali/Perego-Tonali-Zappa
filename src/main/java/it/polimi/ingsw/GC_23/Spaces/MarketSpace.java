package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class MarketSpace extends ActionSpace {
    private static boolean isBusyFirst;

    public MarketSpace(){
        super(0);

    }

    public static boolean isIsBusyFirst() {
        return isBusyFirst;
    }
}
