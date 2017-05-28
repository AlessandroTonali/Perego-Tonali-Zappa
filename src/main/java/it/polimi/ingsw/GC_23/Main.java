package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Controller.ProductionController;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Alessandro on 22/05/2017.
 */
public class Main {

    private ArrayList<Card> cardsList;
    private int period;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Main main = new Main();


    }

    public Main () {
        parseJson();
        ResourcesSet set1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet set2 = new ResourcesSet(5,6,7,8,9,10,11);
        BenefitsEffect prod = new BenefitsEffect(new ResourcesSet(0,5,1,1,0,0,0));
        BenefitsEffect harv = new BenefitsEffect(new ResourcesSet(0,1,1,1,0,0,0));
        BonusTile tile = new BonusTile(prod, harv);
        System.out.println(set1.toString());
        System.out.println(set2.toString());
        Player player = new Player(PlayerColor.RED, null);
        player.setBonusTile(tile);
        player.setResources(set1);
        BenefitsEffect benefitsEffect = new BenefitsEffect(set2);
        benefitsEffect.activeEffect(player);
        System.out.println(player.getResources().toString());
        FamilyMember fam = new FamilyMember(player, FamilyColor.ORANGE);
        fam.setValue(5);
        ProductionSpace space = new ProductionSpace(1);
        ProductionController contr = new ProductionController(fam, space );
        System.out.println(player.getResources());

    }

    public void initializeBoard() {


    }

    private Card getCard (int period) {
        switch (period){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return null;
    }

    private void parseJson() {
        parseCard();

    }

    private void parseCard() {
        String jsonContent = null;
        try {
            Scanner scanner = new Scanner(new File("Cards.txt"));
            jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject rootObject = new JSONObject(jsonContent);

        JSONArray ventureCard = rootObject.getJSONArray("VentureCard");
        for (int x = 0; x < ventureCard.length(); x++) {
            System.out.println(ventureCard.getJSONObject(x).getString("name"));
            System.out.println(ventureCard.getJSONObject(x).getString("cost"));
            String name = ventureCard.getJSONObject(x).getString("name");


        }

        System.out.println();

        JSONArray buildingCard = rootObject.getJSONArray("BuildingCard");
        for (int x = 0; x < buildingCard.length(); x++) {
            System.out.println(buildingCard.getJSONObject(x).getString("name"));
            JSONArray costs = buildingCard.getJSONObject(x).getJSONArray("cost");

            JSONArray immediateEffects = buildingCard.getJSONObject(x).getJSONArray("immediateEffect");
            for (int y = 0; y < immediateEffects.length() ; y++) {

            }


            JSONArray permanentEffects = buildingCard.getJSONObject(x).getJSONArray("permanentEffect");
            for (int y = 0; y < permanentEffects.length() ; y++) {

            }
        }

    }

    public ArrayList<SingleCost> parseCost(JSONArray costs) {
        ArrayList<SingleCost> singleCosts = null;

        for (int y = 0; y < costs.length(); y++) {
            if (costs.getJSONObject(y).has("stone")) {
                System.out.println(costs.getJSONObject(y).getString("stone"));
            }
            if (costs.getJSONObject(y).has("wood")) {
                System.out.println(costs.getJSONObject(y).getString("wood"));
            }
            if (costs.getJSONObject(y).has("coin")) {
                System.out.println(costs.getJSONObject(y).getString("coin"));
            }
            if (costs.getJSONObject(y).has("servant")) {
                System.out.println(costs.getJSONObject(y).getString("servant"));
            }
        }

        SingleCost singleCost = new SingleCost(new ResourcesSet(0,0,0,0,0,0,0));
        singleCosts.add(singleCost);

        return singleCosts;
    }
}
