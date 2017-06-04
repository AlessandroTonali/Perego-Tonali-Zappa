package it.polimi.ingsw.GC_23;
import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Controller.CouncilController;
import it.polimi.ingsw.GC_23.Controller.HarvestController;
import it.polimi.ingsw.GC_23.Controller.ProductionController;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Created by Alessandro on 22/05/2017.
 */
public class Main {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ParseJson parseJson = ParseJson.getParseJson();
        Board.getBoard();

        ArrayList<BuildingCard> buildingCards = parseJson.getBuildingCardArrayList();
        BuildingCard buildingCard = buildingCards.get(0);


        ResourcesSet set1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet set2 = new ResourcesSet(5,6,7,8,9,10,11);
        Player player = new Player(PlayerColor.RED, null);
        player.setResources(set1);
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE,7);
        familyMember.setPlayer(player);
        FamilyMember[] members = new FamilyMember[1];
        members[0] = familyMember;

        System.out.println("Risorse prima dell'effetto: " +player.getResources().toString());
        buildingCard.getImmediateEffect().get(0).activeEffect(player);
        System.out.println("Risorse dopo l'effetto: " +player.getResources().toString());
    }


}
