package it.polimi.ingsw.GC_23.Resources;

import java.lang.reflect.Array;

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
    private static int resourceNumber = 7;



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

    public void setFaithPoints(int faithPoints) {
        this.faithPoints.setQuantity(faithPoints);
    }

    public void setGold(int gold) {
        this.gold.setQuantity(gold);
    }

    public void setMilitaryPoints(int militaryPoints) {
        this.militaryPoints.setQuantity(militaryPoints);
    }

    public void setServants(int servants) {
        this.servants.setQuantity(servants);
    }

    public void setStone(int stone) {
        this.stone.setQuantity(stone);
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints.setQuantity(victoryPoints);
    }

    public void setWood(int wood) {
        this.wood.setQuantity(wood);
    }

    public int getFaithPoints() {
        return faithPoints.getQuantity();
    }

    public int getGold() {
        return gold.getQuantity();
    }

    public int getMilitaryPoints() {
        return militaryPoints.getQuantity();
    }

    public int getServants() {
        return servants.getQuantity();
    }

    public int getStone() {
        return stone.getQuantity();
    }

    public int getVictoryPoints() {return victoryPoints.getQuantity();
    }

    public int getWood() {
        return wood.getQuantity();
    }

    public String toString(){
        return "faith points " + faithPoints.toString() + " military points " + militaryPoints.toString() + " gold " +
                gold.toString() + " servants " + servants.toString() + " wood " + wood.toString() +
                " stone " + stone.toString();

    }

    public int[] getArray(){
        int[] resarray = new int[resourceNumber ];
        resarray[0] = this.getFaithPoints();
        resarray[1] = this.getGold();
        resarray[2] = this.getMilitaryPoints();
        resarray[3] = this.getServants();
        resarray[4] = this.getStone();
        resarray[5] = this.getVictoryPoints();
        resarray[6] = this.getWood();

        return  resarray;


    }

    public boolean checkAffordable( ResourcesSet checked) {
        int[] playerSet = this.getArray();
        int[] checkSet = checked.getArray();
        for(int i = 0; i < resourceNumber; i++) {
            if(playerSet[i] < checkSet[i]){
                return false;
            }
        }
        return true;


    }



    public void setArray(int[] setarray ) {
        int[] resarray = new int[resourceNumber ];
        this.faithPoints.setQuantity(setarray[0]);
        this.gold.setQuantity(setarray[1]);
        this.militaryPoints.setQuantity(setarray[2]);
        this.servants.setQuantity(setarray[3]);
        this.stone.setQuantity(setarray[4]);
        this.victoryPoints.setQuantity(setarray[5]);
        this.wood.setQuantity(setarray[6]);

    }

    public void sum(ResourcesSet prize) {
        int[] playerset = this.getArray();
        int[] prizeset = prize.getArray();
        for(int i = 0; i < resourceNumber; i++){
            playerset[i] = playerset[i] + prizeset[i];
        }
        this.setArray(playerset);
    }

    public void pay(ResourcesSet prize) {
        int[] playerset = this.getArray();
        int[] prizeset = prize.getArray();
        for(int i = 0; i < resourceNumber; i++){
            playerset[i] = playerset[i] - prizeset[i];
        }
        this.setArray(playerset);
    }






}
