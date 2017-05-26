package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Controller.IncreaseFamilyValue;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.FaithPoints;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by Alessandro on 22/05/2017.
 */
public class Main {

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Main main = new Main();

    }

    public Main () {
        parseJson();

        ResourcesSet resset = new ResourcesSet(2,2,2,2,2,2,
                2);
        BenefitsEffect ben = new BenefitsEffect(resset);
        Player player = new Player(PlayerColor.RED, ben);
        player.setResources(resset);
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE);
        System.out.println(player.getResources().toString());
        IncreaseFamilyValue increaseFamilyValue = new IncreaseFamilyValue(1, familyMember);
        System.out.println(player.getResources().toString());
        System.out.println(familyMember.getValue());


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
        String jsonContent = null;
        try {
            jsonContent = new Scanner(new File("Cards.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject rootObject = new JSONObject(jsonContent);

        JSONArray ventureCard = rootObject.getJSONArray("VentureCard");
        for (int x = 0; x < ventureCard.length(); x++) {
            System.out.println(ventureCard.getJSONObject(x).getString("name"));
            System.out.println(ventureCard.getJSONObject(x).getString("cost"));
        }

        System.out.println();

        JSONArray buildingCard = rootObject.getJSONArray("BuildingCard");
        for (int x = 0; x < buildingCard.length(); x++) {
            System.out.println(buildingCard.getJSONObject(x).getString("name"));
            JSONArray costs = buildingCard.getJSONObject(x).getJSONArray("cost");
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
        }







    }
}
