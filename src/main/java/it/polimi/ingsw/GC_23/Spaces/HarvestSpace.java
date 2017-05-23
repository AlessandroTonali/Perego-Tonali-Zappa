package it.polimi.ingsw.GC_23.Spaces;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class HarvestSpace extends ActionSpace {
    private static boolean isBusyFirst;

    public HarvestSpace(int value){
        super(value);
        this.isBusyFirst = false;
    }

    public static boolean isIsBusyFirst() {
        return isBusyFirst;
    }
}
