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
        ParseJson.getParseJson();
        System.out.println( "Hello World!" );
        Board.getBoard();


        ResourcesSet set1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet set2 = new ResourcesSet(5,6,7,8,9,10,11);
        Player player = new Player(PlayerColor.RED, null);
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE,7);
        familyMember.setPlayer(player);
        FamilyMember[] members = new FamilyMember[1];
        members[0] = familyMember;

        player.setResources(set1);
        player.setFamilyMembers(members);
        TowerSpace[] towerSpaces = new TowerSpace[4];
        Tower tower1 = new Tower(towerSpaces);
        Tower tower2 = new Tower(towerSpaces);
        Tower tower3 = new Tower(towerSpaces);
        Tower tower4 = new Tower(towerSpaces);
        Tower[] towers = new Tower[4];
        towers[0] = tower1;
        towers[1] = tower2;
        towers[2] = tower3;
        towers[3] = tower4;
        Board.setTowers(towers);

        BenefitsEffect benefitsEffect1 = new BenefitsEffect(new ResourcesSet(55,1,
                1,1,1,1,1));
        BenefitsEffect benefitsEffect2 = new BenefitsEffect(new ResourcesSet(22,2,2,2,
                2,2,2));
        System.out.println(player.getResources().toString());
        BonusTile bonusTile = new BonusTile(new BenefitsEffect(set1),new BenefitsEffect( set2));
        player.setBonusTile(bonusTile);
        new ProductionController(familyMember,Board.getProductionSpace());
        System.out.println(player.getResources().toString());


        System.out.println("finish");


        System.out.println("ciaoi");

        player.chooseMove();
    }


}
