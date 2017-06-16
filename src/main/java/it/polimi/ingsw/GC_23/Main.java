package it.polimi.ingsw.GC_23;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
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

        TowerSpace towerSpace = new TowerSpace(ParseJson.getParseJson().getBuildingCardArrayList().get(0),
              new BenefitsEffect(new ResourcesSet()), 7  );
        Player player2 = new Player(PlayerColor.BLUE,null);
        FamilyMember familyMember1 = new FamilyMember(player2,FamilyColor.ORANGE,7);
        FamilyMember[] members1 = new FamilyMember[1];
        members1[0] = familyMember1;
        player2.setFamilyMembers(members1);
        familyMember1.setPosition(towerSpace);
        System.out.println(towerSpace.toString());
        System.out.println("ecco qua lo space");



        ResourcesSet set1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet set2 = new ResourcesSet(5,6,7,8,9,10,11);
        Player player1 = new Player(PlayerColor.RED, null);

        Player player3 = new Player(PlayerColor.GREEN,null);
        Player player4 = new Player(PlayerColor.YELLOW,null);
        player1.setResources(set1);




        FamilyMember familyMember = new FamilyMember(player1, FamilyColor.ORANGE,7);
        familyMember.setPlayer(player1);
        FamilyMember[] members = new FamilyMember[1];
        members[0] = familyMember;
        System.out.println("");

        System.out.println("ora qua");
        BenefitsEffect benefitsEffect = new BenefitsEffect(set1);
        ArrayList<AbsEffect> immediate = new ArrayList<>();
        immediate.add(benefitsEffect);
        SingleCost cost = new SingleCost(set2);
        ArrayList<SingleCost> costi = new ArrayList<>();
        costi.add(cost);








        player1.setFamilyMembers(members);
        familyMember.setPlayer(player1);

        familyMember.getFamilyColor();
        Creator creator = new Creator();
        ArrayList<Player> players = new ArrayList<>();
        /*players.add(creator.createPlayer(PlayerColor.RED));
        players.add(creator.createPlayer(PlayerColor.BLUE));
        players.add(creator.createPlayer(PlayerColor.GREEN));
        players.add(creator.createPlayer(PlayerColor.YELLOW));*/
        new Gameplay(players);
        System.out.println("inizio " + player1.getResources().toString());


        System.out.println("fine" + player1.getResources().toString());


    }



}
