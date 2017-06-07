package it.polimi.ingsw.GC_23;

import com.sun.org.apache.bcel.internal.generic.NEW;
import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
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
        arrayList.add(effectMap.get(61));
        arrayList.add(effectMap.get(62));
        arrayList.add(effectMap.get(63));
        arrayList.add(effectMap.get(64));
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

            JSONArray costsJson = characterCards.getJSONObject(i).getJSONArray("cost");
            ArrayList<SingleCost> costs = new ArrayList<>();
            for (int j = 0; j < costsJson.length(); j++) {
                costs.add(parseCost(costsJson.getJSONObject(j)));
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
        for (int i = 0; i < newPlayHarvestEffects.length(); i++) {
            int idEffect = newPlayHarvestEffects.getJSONObject(i).getInt("id");
            int diceValue = newPlayHarvestEffects.getJSONObject(i).getInt("dice_value");
            NewPlayHarvestEffect newPlayHarvestEffect = new NewPlayHarvestEffect(diceValue);
            effectMap.put(idEffect, newPlayHarvestEffect);
        }
    }

    private void parseNewPlayProductionEffect(JSONArray newPlayProductionEffects) {
        for (int i = 0; i < newPlayProductionEffects.length(); i++) {
            int idEffect = newPlayProductionEffects.getJSONObject(i).getInt("id");
            int diceValue = newPlayProductionEffects.getJSONObject(i).getInt("dice_value");
            NewPlayProductionEffect newPlayProductionEffect = new NewPlayProductionEffect(diceValue);
            effectMap.put(idEffect, newPlayProductionEffect);
        }
    }

    private void parseNewPlayCardEffect(JSONArray newPlayEffects) {
        for (int i = 0; i < newPlayEffects.length(); i++) {
            ArrayList<SingleCost> sale = new ArrayList<>();
            NewPlayColor newPlayColor = null;
            int idEffect = newPlayEffects.getJSONObject(i).getInt("id");
            int diceValue = newPlayEffects.getJSONObject(i).getInt("dice_value");
            String towerColor = newPlayEffects.getJSONObject(i).getString("tower_color");
            if (newPlayEffects.getJSONObject(i).has("sale")) {
                JSONArray sales = newPlayEffects.getJSONObject(i).getJSONArray("sale");
                for (int j = 0; j < sales.length(); j++) {
                    sale.add(parseCost(sales.getJSONObject(j)));
                }
            }
            if (towerColor.equals("green")) {
                newPlayColor = NewPlayColor.GREEN;
            }
            if (towerColor.equals("yellow")) {
                newPlayColor = NewPlayColor.YELLOW;
            }
            if (towerColor.equals("purple")) {
                newPlayColor = NewPlayColor.PURPLE;
            }
            if (towerColor.equals("blue")) {
                newPlayColor = NewPlayColor.BLUE;
            }
            if (towerColor.equals("rainbow")) {
                newPlayColor = NewPlayColor.RAINBOW;
            }
            NewPlayCardEffect newPlayCardEffect = new NewPlayCardEffect(diceValue, newPlayColor, sale);
            effectMap.put(idEffect, newPlayCardEffect);
        }

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

            if (!jsonObject.has("required_product")) {

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
            } else {
                JSONArray jsonArrayRequired = jsonObject.getJSONArray("required_product");
                ArrayList<SingleCost> requires = new ArrayList<>();
                for (int j = 0; j < jsonArrayRequired.length(); j++) {
                    requires.add(parseCost(jsonArrayRequired.getJSONObject(j)));
                }
                productEffect = new ProductEffect(givings.get(0), requires.get(0));
            }
            
            effectMap.put(idEffect, productEffect);

        }
    }

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

    public BenefitsEffect[] getCouncilBenefit() {
        BenefitsEffect[] councilPrivilegeBenefit = new BenefitsEffect[5];
        councilPrivilegeBenefit[0] = benefitsEffectMap.get(10);
        councilPrivilegeBenefit[1] = benefitsEffectMap.get(11);
        councilPrivilegeBenefit[2] = benefitsEffectMap.get(12);
        councilPrivilegeBenefit[3] = benefitsEffectMap.get(13);
        councilPrivilegeBenefit[4] = benefitsEffectMap.get(14);

        return councilPrivilegeBenefit;
    }

    public ArrayList<AbsEffect> getCouncilSpaceEffect() {
        ArrayList<AbsEffect> councilEffects  = new ArrayList<>();
        councilEffects.add(effectMap.get(1));
        councilEffects.add(effectMap.get(2));

        return councilEffects ;
    }

    public BonusTile getBonusTile1() {
        return new BonusTile((BenefitsEffect) effectMap.get(70), (BenefitsEffect) effectMap.get(71));
    }
    public BonusTile getBonusTile2() {
        return new BonusTile((BenefitsEffect) effectMap.get(72), (BenefitsEffect) effectMap.get(73));
    }
    public BonusTile getBonusTile3() {
        return new BonusTile((BenefitsEffect) effectMap.get(74), (BenefitsEffect) effectMap.get(75));
    }
    public BonusTile getBonusTile4() {
        return new BonusTile((BenefitsEffect) effectMap.get(76), (BenefitsEffect) effectMap.get(77));
    }
    public BonusTile getBonusTile5() {
        return new BonusTile((BenefitsEffect) effectMap.get(78), (BenefitsEffect) effectMap.get(79));
    }

    public ArrayList<CharacterCard> getCharacterCardArrayList() {
        return new ArrayList<CharacterCard>(characterCardArrayList);
    }

    public ArrayList<TerritoryCard> getTerritoryCardArrayList() {
        return new ArrayList<TerritoryCard>(territoryCardArrayList);
    }

    public ArrayList<VentureCard> getVentureCardArrayList() {
        return new ArrayList<VentureCard>(ventureCardArrayList);
    }

    public ArrayList<BuildingCard> getBuildingCardArrayList() {
        return new ArrayList<BuildingCard>(buildingCardArrayList);
    }
}
