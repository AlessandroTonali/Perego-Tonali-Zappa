package it.polimi.ingsw.GC_23;
import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Controller.CouncilController;
import it.polimi.ingsw.GC_23.Controller.HarvestController;
import it.polimi.ingsw.GC_23.Controller.OtherCardsController;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Created by Alessandro on 22/05/2017.
 */
public class Main {
    public static void main( String[] args ) throws IOException {

        ParseJson.getParseJson();

        Board board = new Board(2);

        TowerSpace towerSpace = new TowerSpace(ParseJson.getParseJson().getBuildingCardArrayList().get(0),
              new BenefitsEffect(new ResourcesSet()), 7  );
        Player player2 = new Player(PlayerColor.BLUE,null);
        FamilyMember familyMember1 = new FamilyMember(player2,FamilyColor.ORANGE,7);
        FamilyMember[] members1 = new FamilyMember[1];
        members1[0] = familyMember1;
        player2.setFamilyMembers(members1);
        familyMember1.setPosition(towerSpace);
        System.out.println(towerSpace.toString());
    }



}
