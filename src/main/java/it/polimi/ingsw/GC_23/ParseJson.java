package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Controller.NewPlay;
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

    private HashMap<Integer, AbsEffect> effectMap;
    private HashMap<Integer,BenefitsEffect> benefitsEffectMap;

    public ParseJson() {
        parseCard();
        parseEffect();
        BenefitsEffect benefitsEffect = (BenefitsEffect) effectMap.get(401);


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

        JSONArray buildingCards = rootObject.getJSONArray("BuildingCard");
        for (int x = 0; x < buildingCards.length(); x++) {
            System.out.println(buildingCards.getJSONObject(x).getString("name"));
            JSONArray costsJson = buildingCards.getJSONObject(x).getJSONArray("cost");

            ArrayList<SingleCost> costs = new ArrayList<>();
            ArrayList<Integer> immediateEffectId = new ArrayList<>();

            for (int i = 0; i < costsJson.length(); i++) {
                costs.add(parseCost(costsJson.getJSONObject(i)));
            }

            JSONArray immediateEffectsJson = buildingCards.getJSONObject(x).getJSONArray("immediateEffect");
            Effect immediateEffect = parseTypeEffect(immediateEffectsJson);



            JSONArray permanentEffectsJson = buildingCards.getJSONObject(x).getJSONArray("permanentEffect");
            Effect permanentEffect = parseTypeEffect(permanentEffectsJson);

            String name = buildingCards.getJSONObject(x).getString("name");
            int period = buildingCards.getJSONObject(x).getInt("period");
            int idCard = buildingCards.getJSONObject(x).getInt("id");
            BuildingCard buildingCard = new BuildingCard(period, CardColor.YELLOW, name, immediateEffect, permanentEffect, costs);
        }

    }

    public Effect parseTypeEffect(JSONArray jsonArray) {
        BenefitsEffect benefitsEffect = null;
        CouncilPrivilegeEffect councilPrivilegeEffect = null;
        DiscountEffect discountEffect = null;
        ImplicationEffect implicationEffect = null;
        NewPlayEffect newPlayEffect = null;
        for (int y = 0; y < jsonArray.length() ; y++) {

            int id = jsonArray.getJSONObject(y).getInt("effect");

            switch (effectMap.get(id).getTyteEffect()) {
                case EffectType.BENEFIT_EFFECT_TYPE:
                    benefitsEffect = (BenefitsEffect) effectMap.get(id);
                    break;
                case EffectType.COUNCIL_EFFECT_TYPE:
                    councilPrivilegeEffect = (CouncilPrivilegeEffect) effectMap.get(id);
                    break;
                case EffectType.DISCOUNT_EFFECT_TYPE:
                    discountEffect = (DiscountEffect) effectMap.get(id);
                    break;
                case EffectType.IMPLICATION_EFFECT_TYPE:
                    implicationEffect = (ImplicationEffect) effectMap.get(id);
                    break;
                case EffectType.NEWPLAY_EFFECT_TYPE:
                    newPlayEffect = (NewPlayEffect) effectMap.get(id);
                    break;
            }
        }
        Effect effect = new Effect(councilPrivilegeEffect, benefitsEffect, implicationEffect, newPlayEffect, discountEffect);

        return effect;

    }

    public void parseEffect() {
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

        JSONArray newPlayEffects = rootObject.getJSONArray("NewPlayEffect");
        parseNewPlayEffect(newPlayEffects);
    }

    private void parseNewPlayEffect(JSONArray newPlayEffects) {

    }

    public void parseBenefitEffect(JSONArray benefitEffects) {
        for (int i = 0; i < benefitEffects.length() ; i++) {
            JSONObject jsonObject = benefitEffects.getJSONObject(i);
            BenefitsEffect benefitsEffect = new BenefitsEffect(parseCost(jsonObject).getResources());
            benefitsEffectMap.put(jsonObject.getInt("id"), benefitsEffect);
            effectMap.put(jsonObject.getInt("id"), benefitsEffect);

            //System.out.println(effect.getResources().toString());
        }
    }

    private void parseCouncilPrivilegeEffect(JSONArray councilPrivilegeEffects) {
        BenefitsEffect[] councilPrivilege = new BenefitsEffect[5];
        councilPrivilege[0] = benefitsEffectMap.get("10");
        councilPrivilege[1] = benefitsEffectMap.get("11");
        councilPrivilege[2] = benefitsEffectMap.get("12");
        councilPrivilege[3] = benefitsEffectMap.get("13");
        councilPrivilege[4] = benefitsEffectMap.get("14");
        for (int i = 0; i < councilPrivilegeEffects.length(); i++) {
            JSONObject jsonObject = councilPrivilegeEffects.getJSONObject(i);
            CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(councilPrivilege,jsonObject.getInt("number_privilege"), jsonObject.getBoolean("is_different"));
            effectMap.put(jsonObject.getInt("id"), councilPrivilegeEffect);

        }
    }

    private void parseImplicationEffect(JSONArray implicationEffects) {
        for (int i = 0; i < implicationEffects.length() ; i++) {
            ArrayList<SingleCost> requirments = new ArrayList<>();
            ArrayList<BenefitsEffect> givings = new ArrayList<>();
            JSONObject jsonObject = implicationEffects.getJSONObject(i);
            JSONArray jsonArrayRequirment = jsonObject.getJSONArray("requirment");
            for (int j = 0; j < jsonArrayRequirment.length(); j++) {
                requirments.add(parseCost(jsonArrayRequirment.getJSONObject(j)));
            }

            JSONArray jsonArrayGiving = jsonObject.getJSONArray("giving");
            for (int j = 0; j < jsonArrayGiving.length(); j++) {
                BenefitsEffect benefitsEffect = new BenefitsEffect(parseCost(jsonArrayGiving.getJSONObject(j)).getResources());
                givings.add(benefitsEffect);
            }

            ImplicationEffect implicationEffect =  new ImplicationEffect(requirments,givings);
            effectMap.put(jsonObject.getInt("id"), implicationEffect);

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

    public SingleCost parseCost(JSONObject jsonObject) {
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
}
