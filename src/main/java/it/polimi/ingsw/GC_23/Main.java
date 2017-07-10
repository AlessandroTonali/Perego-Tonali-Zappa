package it.polimi.ingsw.GC_23;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.*;
import java.io.IOException;


/**
 * Created by Alessandro on 22/05/2017.
 */
public class Main {
    public static void main( String[] args ) throws IOException {

        ParseJson.getParseJson();

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
