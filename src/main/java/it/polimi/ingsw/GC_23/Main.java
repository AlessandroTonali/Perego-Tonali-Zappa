package it.polimi.ingsw.GC_23;
import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Controller.HarvestController;
import it.polimi.ingsw.GC_23.Controller.ProductionController;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.HarvestSpace;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;
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
        ParseJson parseJson = new ParseJson();
        System.out.println( "Hello World!" );
        ResourcesSet set1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet set2 = new ResourcesSet(5,6,7,8,9,10,11);
    }

    public Main () {
    }
}
