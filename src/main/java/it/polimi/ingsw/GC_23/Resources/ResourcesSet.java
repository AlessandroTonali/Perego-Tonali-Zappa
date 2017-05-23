package it.polimi.ingsw.GC_23.Resources;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class ResourcesSet {
    private FaithPoints faithPoints;
    private Gold gold;
    private MilitaryPoints militaryPoints;
    private Servants servants;
    private Stone stone;
    private VictoryPoints victoryPoints;
    private Wood wood;



    public ResourcesSet(int faithPoints, int gold, int militaryPoints, int servants,
                        int stone, int victoryPoints, int wood) {
        this.faithPoints = new FaithPoints(faithPoints);
        this.gold = new Gold(gold);
        this.militaryPoints = new MilitaryPoints(militaryPoints);
        this.servants = new Servants(servants);
        this.stone = new Stone(stone);
        this.victoryPoints = new VictoryPoints(victoryPoints);
        this.wood = new Wood(wood);
    }

    public FaithPoints getFaithPoints() {
        return faithPoints;
    }

    public Gold getGold() {
        return gold;
    }

    public MilitaryPoints getMilitaryPoints() {
        return militaryPoints;
    }

    public Servants getServants() {
        return servants;
    }

    public Stone getStone() {
        return stone;
    }

    public VictoryPoints getVictoryPoints() {
        return victoryPoints;
    }

    public Wood getWood() {
        return wood;
    }

    public String toString(){
        return "faith points " + faithPoints.toString() + " military points " + militaryPoints.toString() + " gold " +
                gold.toString() + " servants " + servants.toString() + " wood " + wood.toString() +
                " stone " + stone.toString();
    }




}
