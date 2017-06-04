package it.polimi.ingsw.GC_23.Enumerations;

/**
 * Created by jesss on 21/05/17.
 */
public enum PlayerColor {
    BLUE ("BLUE"),
    GREEN ("GREEN"),
    YELLOW ("YELLOW"),
    RED ("RED");

    private final String name;

    private PlayerColor(String s){
        name=s;
    }

    public boolean equalsName(String otherName){
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}

