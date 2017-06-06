package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alessandro on 01/06/2017.
 */
public class ParseJson {
    private static ParseJson parseJson;

    private HashMap<Integer, AbsEffect> effectMap;
    private HashMap<Integer, BenefitsEffect> benefitsEffectMap;
    private HashMap<Integer,BuildingCard> buildingCardMap = new HashMap<>();
    private HashMap<Integer,VentureCard> ventureCardMap = new HashMap<>();
    private HashMap<Integer,TerritoryCard> territoryCardMap = new HashMap<>();
    private HashMap<Integer,CharacterCard> characterCardMap = new HashMap<>();
    private ArrayList<CharacterCard> characterCardArrayList = new ArrayList<>();
    private ArrayList<TerritoryCard> territoryCardArrayList = new ArrayList<>();
    private ArrayList<VentureCard> ventureCardArrayList = new ArrayList<>();
    private ArrayList<BuildingCard> buildingCardArrayList = new ArrayList<>();

    public static synchronized ParseJson getParseJson(){
        if(parseJson == null){
            parseJson = new ParseJson();
        }
        return parseJson;

    }

    private ParseJson() {
        parseEffect();
        parseCard();


    }

    public ArrayList<AbsEffect> getMarketEffect() {

        ArrayList<AbsEffect> arrayList = new ArrayList<>();
        arrayList.add(effectMap.get("61"));
        arrayList.add(effectMap.get("62"));
        arrayList.add(effectMap.get("63"));
        arrayList.add(effectMap.get("64"));
        return arrayList;

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

        JSONArray ventureCards = rootObject.getJSONArray("VentureCard");
        for (int x = 0; x < ventureCards.length(); x++) {
            String name = ventureCards.getJSONObject(x).getString("name");
            int idCard = ventureCards.getJSONObject(x).getInt("id");
            int period = ventureCards.getJSONObject(x).getInt("period");

            JSONArray costsJson = ventureCards.getJSONObject(x).getJSONArray("cost");
            ArrayList<SingleCost> costs = new ArrayList<>();
            for (int i = 0; i < costsJson.length(); i++) {
                costs.add(parseCost(costsJson.getJSONObject(i)));
            }

            JSONArray immediateEffectsJson = ventureCards.getJSONObject(x).getJSONArray("immediateEffect");
            ArrayList<AbsEffect> immediateEffect = parseTypeEffect(immediateEffectsJson);

            JSONArray permanentEffectsJson = ventureCards.getJSONObject(x).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffect = parseTypeEffect(permanentEffectsJson);

            VentureCard ventureCard = new VentureCard(period, CardColor.PURPLE, name, immediateEffect, permanentEffect, costs);
            ventureCardMap.put(idCard, ventureCard);
            ventureCardArrayList.add(ventureCard);


        }

        System.out.println();

        JSONArray buildingCards = rootObject.getJSONArray("BuildingCard");
        for (int x = 0; x < buildingCards.length(); x++) {

            JSONArray costsJson = buildingCards.getJSONObject(x).getJSONArray("cost");
            ArrayList<SingleCost> costs = new ArrayList<>();
            for (int i = 0; i < costsJson.length(); i++) {
                costs.add(parseCost(costsJson.getJSONObject(i)));
            }

            JSONArray immediateEffectsJson = buildingCards.getJSONObject(x).getJSONArray("immediateEffect");
            ArrayList<AbsEffect> immediateEffect = parseTypeEffect(immediateEffectsJson);

            JSONArray permanentEffectsJson = buildingCards.getJSONObject(x).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffect = parseTypeEffect(permanentEffectsJson);

            String name = buildingCards.getJSONObject(x).getString("name");
            int period = buildingCards.getJSONObject(x).getInt("period");
            int idCard = buildingCards.getJSONObject(x).getInt("id");
            int harvestValue = buildingCards.getJSONObject(x).getInt("harvest_value");
            BuildingCard buildingCard = new BuildingCard(period, CardColor.YELLOW, name, immediateEffect, permanentEffect, costs, harvestValue);
            buildingCardMap.put(idCard,buildingCard);
            buildingCardArrayList.add(buildingCard);

        }


        JSONArray territoryCards = rootObject.getJSONArray("TerritoryCard");
        for (int i = 0; i < territoryCards.length(); i++) {
            String name = territoryCards.getJSONObject(i).getString("name");
            int idCard = territoryCards.getJSONObject(i).getInt("id");
            int period = territoryCards.getJSONObject(i).getInt("period");
            int productionValue = territoryCards.getJSONObject(i).getInt("production_value");
            JSONArray immediateEffectsJson = territoryCards.getJSONObject(i).getJSONArray("immediateEffect");
            ArrayList<AbsEffect> immediateEffect = parseTypeEffect(immediateEffectsJson);
            JSONArray permanentEffectsJson = territoryCards.getJSONObject(i).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffect = parseTypeEffect(permanentEffectsJson);

            TerritoryCard territoryCard = new TerritoryCard(period, CardColor.GREEN, name, immediateEffect,permanentEffect, productionValue);
            territoryCardMap.put(idCard,territoryCard);
            territoryCardArrayList.add(territoryCard);
        }

        JSONArray characterCards = rootObject.getJSONArray("CharacterCard");
        for (int i = 0; i < characterCards.length(); i++) {
            int idCard = characterCards.getJSONObject(i).getInt("id");
            String name = characterCards.getJSONObject(i).getString("name");
            int period = characterCards.getJSONObject(i).getInt("period");

            JSONArray costsJson = buildingCards.getJSONObject(i).getJSONArray("cost");
            ArrayList<SingleCost> costs = new ArrayList<>();
            for (int j = 0; j < costsJson.length(); j++) {
                costs.add(parseCost(costsJson.getJSONObject(i)));
            }

            JSONArray immediateEffectsJson = characterCards.getJSONObject(i).getJSONArray("immediateEffect");
            ArrayList<AbsEffect> immediateEffect = parseTypeEffect(immediateEffectsJson);
            JSONArray permanentEffectsJson = characterCards.getJSONObject(i).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffect = parseTypeEffect(permanentEffectsJson);

            CharacterCard characterCard = new CharacterCard(period, CardColor.BLUE, name, immediateEffect, permanentEffect, costs);
            characterCardMap.put(idCard, characterCard);
            characterCardArrayList.add(characterCard);

        }

    }

    private ArrayList<AbsEffect> parseTypeEffect(JSONArray jsonArray) {
        ArrayList<AbsEffect> arrayList = new ArrayList<>();
        for (int y = 0; y < jsonArray.length() ; y++) {

            int id = jsonArray.getJSONObject(y).getInt("effect");
            arrayList.add(effectMap.get(id));
        }

        return arrayList;
    }

    public AbsEffect[] getTowerTerritoryEffect(){
        AbsEffect[] towerEffect = new AbsEffect[4];
        towerEffect[0] = effectMap.get(0);
        towerEffect[1] = effectMap.get(0);
        towerEffect[2] = effectMap.get(23);
        towerEffect[3] = effectMap.get(24);

        return towerEffect;
    }

    public AbsEffect[] getTowerCharacterEffect(){
        AbsEffect[] towerEffect = new AbsEffect[4];
        towerEffect[0] = effectMap.get(0);
        towerEffect[1] = effectMap.get(0);
        towerEffect[2] = effectMap.get(33);
        towerEffect[3] = effectMap.get(34);

        return towerEffect;
    }

    public AbsEffect[] getTowerVentureEffect(){
        AbsEffect[] towerEffect = new AbsEffect[4];
        towerEffect[0] = effectMap.get(0);
        towerEffect[1] = effectMap.get(0);
        towerEffect[2] = effectMap.get(43);
        towerEffect[3] = effectMap.get(44);

        return towerEffect;
    }

    public AbsEffect[] getTowerBuildingEffect(){
        AbsEffect[] towerEffect = new AbsEffect[4];
        towerEffect[0] = effectMap.get(0);
        towerEffect[1] = effectMap.get(0);
        towerEffect[2] = effectMap.get(53);
        towerEffect[3] = effectMap.get(54);

        return towerEffect;
    }

    private void parseEffect() {
        String jsonContent = null;
        effectMap = new HashMap<Integer,AbsEffect>();
        try {
            Scanner scanner = new Scanner(new File("Effect.txt"));
            jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        benefitsEffectMap = new HashMap<Integer,BenefitsEffect>();


        JSONObject rootObject = new JSONObject(jsonContent);

        JSONArray benefitEffects = rootObject.getJSONArray("BenefitEffect");
        parseBenefitEffect(benefitEffects);



        JSONArray councilPrivilegeEffects = rootObject.getJSONArray("CouncilPrivilegeEffect");
        parseCouncilPrivilegeEffect(councilPrivilegeEffects);


        JSONArray implicationEffects = rootObject.getJSONArray("ImplicationEffect");
        parseImplicationEffect(implicationEffects);

        JSONArray discountEffects = rootObject.getJSONArray("DiscountEffect");

        JSONArray newPlayCardEffects = rootObject.getJSONArray("NewPlayCardEffect");
        parseNewPlayCardEffect(newPlayCardEffects);

        JSONArray newPlayProductionEffects = rootObject.getJSONArray("NewPlayProductionEffect");
        parseNewPlayProductionEffect(newPlayProductionEffects);

        JSONArray newPlayHarvestEffects = rootObject.getJSONArray("NewPlayProductionEffect");
        parseNewPlayHarvestEffect(newPlayHarvestEffects);
        



        JSONArray productEffects = rootObject.getJSONArray("ProductEffect");
        parseProductEffect(productEffects);
    }

    private void parseNewPlayHarvestEffect(JSONArray newPlayHarvestEffects) {
    }

    private void parseNewPlayProductionEffect(JSONArray newPlayProductionEffects) {
    }

    private void parseNewPlayCardEffect(JSONArray newPlayEffects) {

    }

    private void parseBenefitEffect(JSONArray benefitEffects) {
        for (int i = 0; i < benefitEffects.length() ; i++) {
            JSONObject jsonObject = benefitEffects.getJSONObject(i);
            BenefitsEffect benefitsEffect = new BenefitsEffect(parseCost(jsonObject).getResources());
            benefitsEffectMap.put(jsonObject.getInt("id"), benefitsEffect);
            effectMap.put(jsonObject.getInt("id"), benefitsEffect);
        }
    }

    private void parseCouncilPrivilegeEffect(JSONArray councilPrivilegeEffects) {
        for (int i = 0; i < councilPrivilegeEffects.length(); i++) {
            JSONObject jsonObject = councilPrivilegeEffects.getJSONObject(i);
            CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(jsonObject.getInt("number_privilege"), jsonObject.getBoolean("is_different"));
            councilPrivilegeEffect.setBenefits(getCouncilBenefit());
            effectMap.put(jsonObject.getInt("id"), councilPrivilegeEffect);

        }
    }

    public BenefitsEffect[] getCouncilBenefit() {
        BenefitsEffect[] councilPrivilegeBenefit = new BenefitsEffect[5];
        councilPrivilegeBenefit[0] = benefitsEffectMap.get("10");
        councilPrivilegeBenefit[1] = benefitsEffectMap.get("11");
        councilPrivilegeBenefit[2] = benefitsEffectMap.get("12");
        councilPrivilegeBenefit[3] = benefitsEffectMap.get("13");
        councilPrivilegeBenefit[4] = benefitsEffectMap.get("14");

        return councilPrivilegeBenefit;
    }

    public ArrayList<AbsEffect> getCouncilSpaceEffect() {
        ArrayList<AbsEffect> councilEffects  = new ArrayList<>();
        councilEffects.add(effectMap.get("1"));
        councilEffects.add(effectMap.get("2"));

        return councilEffects ;
    }

    private void parseImplicationEffect(JSONArray implicationEffects) {
        for (int i = 0; i < implicationEffects.length() ; i++) {
            ArrayList<SingleCost> requirments = new ArrayList<>();
            ArrayList<AbsEffect> givings = new ArrayList<>();
            JSONObject jsonObject = implicationEffects.getJSONObject(i);
            JSONArray jsonArrayRequirment = jsonObject.getJSONArray("requirment");
            for (int j = 0; j < jsonArrayRequirment.length(); j++) {
                requirments.add(parseCost(jsonArrayRequirment.getJSONObject(j)));
            }

            JSONArray jsonArrayGiving = jsonObject.getJSONArray("giving");
            for (int j = 0; j < jsonArrayGiving.length(); j++) {
                AbsEffect effect;
                if (jsonArrayGiving.getJSONObject(j).has("id_effect")) {
                    effect = effectMap.get(jsonArrayGiving.getJSONObject(j).getInt("id_effect"));
                } else {
                    effect = new BenefitsEffect(parseCost(jsonArrayGiving.getJSONObject(j)).getResources());
                }
                givings.add(effect);
            }

            ImplicationEffect implicationEffect =  new ImplicationEffect(requirments,givings);
            effectMap.put(jsonObject.getInt("id"), implicationEffect);

        }
    }

    private void parseProductEffect(JSONArray productEffects) {
        for (int i = 0; i < productEffects.length(); i++) {
            ProductEffect productEffect = null;
            int idEffect = productEffects.getJSONObject(i).getInt("id");

            ArrayList<SingleCost> givings = new ArrayList<>();
            JSONObject jsonObject = productEffects.getJSONObject(i);
            JSONArray jsonArrayGiving = jsonObject.getJSONArray("giving_product");
            for (int j = 0; j < jsonArrayGiving.length(); j++) {
                givings.add(parseCost(jsonArrayGiving.getJSONObject(j)));
            }

            String cardColor = productEffects.getJSONObject(i).getString("card_color");
            if (cardColor.equals("green")) {

                productEffect = new ProductEffect(givings.get(0), CardColor.GREEN);
            }
            if (cardColor.equals("yellow")) {
                productEffect = new ProductEffect(givings.get(0), CardColor.YELLOW);
            }
            if (cardColor.equals("purple")) {
                productEffect = new ProductEffect(givings.get(0), CardColor.PURPLE);
            }
            if (cardColor.equals("blue")) {
                productEffect = new ProductEffect(givings.get(0), CardColor.BLUE);
            }
            
            effectMap.put(idEffect, productEffect);

        }
    }



    /*public BenefitsEffect parseBenefit(JSONObject jsonObject) {
        int faithPoint = jsonObject.getInt("faithPoint");
        int coin = jsonObject.getInt("coin");
        int militaryPoint = jsonObject.getInt("militaryPoint");
        int servant = jsonObject.getInt("servant");
        int stone = jsonObject.getInt("stone");
        int victoryPoint = jsonObject.getInt("victoryPoint");
        int wood = jsonObject.getInt("wood");
        ResourcesSet resources = new ResourcesSet(faithPoint, coin, militaryPoint, servant, stone, victoryPoint, wood);
        BenefitsEffect benefitsEffect = new BenefitsEffect(resources);

        return benefitsEffect;


    }*/

    private SingleCost parseCost(JSONObject jsonObject) {
        int faithPoint = 0;
        int coin = 0;
        int militaryPoint = 0;
        int servant = 0;
        int stone = 0;
        int victoryPoint = 0;
        int wood = 0;


        if (jsonObject.has("stone")) {
            stone = jsonObject.getInt("stone");
        }
        if (jsonObject.has("wood")) {
            wood = jsonObject.getInt("wood");
        }
        if (jsonObject.has("coin")) {
            coin = jsonObject.getInt("coin");
        }
        if (jsonObject.has("servant")) {
            servant = jsonObject.getInt("servant");
        }
        if (jsonObject.has("faithPoint")) {
            faithPoint = jsonObject.getInt("faithPoint");
        }
        if (jsonObject.has("militaryPoint")) {
            militaryPoint = jsonObject.getInt("militaryPoint");
        }
        if (jsonObject.has("victoryPoint")) {
            victoryPoint = jsonObject.getInt("victoryPoint");
        }

        SingleCost singleCost = new SingleCost(new ResourcesSet(faithPoint,coin,militaryPoint,servant,stone,victoryPoint,wood));

        return singleCost;
    }

    public BonusTile getBonusTile1() {
        return new BonusTile((BenefitsEffect) effectMap.get("70"), (BenefitsEffect) effectMap.get("71"));
    }
    public BonusTile getBonusTile2() {
        return new BonusTile((BenefitsEffect) effectMap.get("72"), (BenefitsEffect) effectMap.get("73"));
    }
    public BonusTile getBonusTile3() {
        return new BonusTile((BenefitsEffect) effectMap.get("74"), (BenefitsEffect) effectMap.get("75"));
    }
    public BonusTile getBonusTile4() {
        return new BonusTile((BenefitsEffect) effectMap.get("76"), (BenefitsEffect) effectMap.get("77"));
    }
    public BonusTile getBonusTile5() {
        return new BonusTile((BenefitsEffect) effectMap.get("78"), (BenefitsEffect) effectMap.get("79"));
    }

    public ArrayList<CharacterCard> getCharacterCardArrayList() {
        return characterCardArrayList;
    }

    public ArrayList<TerritoryCard> getTerritoryCardArrayList() {
        return territoryCardArrayList;
    }

    public ArrayList<VentureCard> getVentureCardArrayList() {
        return ventureCardArrayList;
    }

    public ArrayList<BuildingCard> getBuildingCardArrayList() {
        return buildingCardArrayList;
    }
}
