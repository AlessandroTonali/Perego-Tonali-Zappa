package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private HashMap<Integer, LeaderCard> leaderCardMap = new HashMap<>();
    private ArrayList<Card> characterCardArrayList = new ArrayList<>();
    private ArrayList<Card> territoryCardArrayList = new ArrayList<>();
    private ArrayList<Card> ventureCardArrayList = new ArrayList<>();
    private ArrayList<Card> buildingCardArrayList = new ArrayList<>();
    private ArrayList<ExcommunicationTile> excommunicationTileFirstPeriod = new ArrayList<>();
    private ArrayList<ExcommunicationTile> excommunicationTileSecondPeriod = new ArrayList<>();
    private ArrayList<ExcommunicationTile> excommunicationTileThirdPeriod = new ArrayList<>();
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public static synchronized ParseJson getParseJson(){
        if(parseJson == null){
            parseJson = new ParseJson();
        }
        return parseJson;

    }

    private ParseJson() {
        effectMap = new HashMap<Integer,AbsEffect>();
        parseImmediateEffect();
        parsePermanentEffect();
        parseCard();
        parseBoardComponent();


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
            Scanner scanner = new Scanner(new File("Cards.json"));
            jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
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
            ArrayList<AbsEffect> immediateEffect = parsingEffect(immediateEffectsJson);

            JSONArray permanentEffectsJson = ventureCards.getJSONObject(x).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffects = parsingEffect(permanentEffectsJson);



            VentureCard ventureCard = new VentureCard(period, CardColor.PURPLE, name, immediateEffect, permanentEffects, costs);
            ventureCardMap.put(idCard, ventureCard);
            ventureCardArrayList.add(ventureCard);


        }

        JSONArray buildingCards = rootObject.getJSONArray("BuildingCard");
        for (int x = 0; x < buildingCards.length(); x++) {

            String name = buildingCards.getJSONObject(x).getString("name");
            int period = buildingCards.getJSONObject(x).getInt("period");
            int idCard = buildingCards.getJSONObject(x).getInt("id");

            JSONArray costsJson = buildingCards.getJSONObject(x).getJSONArray("cost");
            ArrayList<SingleCost> costs = new ArrayList<>();
            for (int i = 0; i < costsJson.length(); i++) {
                costs.add(parseCost(costsJson.getJSONObject(i)));
            }

            JSONArray immediateEffectsJson = buildingCards.getJSONObject(x).getJSONArray("immediateEffect");
            ArrayList<AbsEffect> immediateEffect = parsingEffect(immediateEffectsJson);

            JSONArray permanentEffectsJson = buildingCards.getJSONObject(x).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffects = parsingEffect(permanentEffectsJson);

            BuildingCard buildingCard = new BuildingCard(period, CardColor.YELLOW, name, immediateEffect, permanentEffects, costs);
            buildingCardMap.put(idCard,buildingCard);
            buildingCardArrayList.add(buildingCard);

        }


        JSONArray territoryCards = rootObject.getJSONArray("TerritoryCard");
        for (int i = 0; i < territoryCards.length(); i++) {
            String name = territoryCards.getJSONObject(i).getString("name");
            int idCard = territoryCards.getJSONObject(i).getInt("id");
            int period = territoryCards.getJSONObject(i).getInt("period");
            JSONArray immediateEffectsJson = territoryCards.getJSONObject(i).getJSONArray("immediateEffect");
            ArrayList<AbsEffect> immediateEffect = parsingEffect(immediateEffectsJson);
            JSONArray permanentEffectsJson = territoryCards.getJSONObject(i).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffects = parsingEffect(permanentEffectsJson);

            TerritoryCard territoryCard = new TerritoryCard(period, CardColor.GREEN, name, immediateEffect, permanentEffects);
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
            ArrayList<AbsEffect> immediateEffect = parsingEffect(immediateEffectsJson);
            JSONArray permanentEffectsJson = characterCards.getJSONObject(i).getJSONArray("permanentEffect");
            ArrayList<AbsEffect> permanentEffects = parsingEffect(permanentEffectsJson);

            CharacterCard characterCard = new CharacterCard(period, CardColor.BLUE, name, immediateEffect, permanentEffects, costs);
            characterCardMap.put(idCard, characterCard);
            characterCardArrayList.add(characterCard);

        }

        JSONArray leaderCards = rootObject.getJSONArray("LeaderCard");
        for (int i = 0; i < leaderCards.length(); i++) {
            JSONObject jsonObject = leaderCards.getJSONObject(i);

            int idCard =  jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            Requirement requirement = parseRequirement(jsonObject.getJSONObject("requirement"));
            ArrayList<AbsEffect> effects = parsingEffect(jsonObject.getJSONArray("effect"));

            LeaderCard leaderCard = new LeaderCard(name, requirement, effects);
            leaderCardMap.put(idCard, leaderCard);
        }

    }

    private Requirement parseRequirement(JSONObject requirementJsonObject) {
        int numberVenture = 0;
        int numberCharacter = 0;
        int numberBuilding = 0;
        int numberTerritory = 0;
        ResourcesSet resources = new ResourcesSet(0, 0, 0, 0, 0, 0, 0);

        if (requirementJsonObject.has("venture")) {
            numberVenture = requirementJsonObject.getInt("venture");
        }

        if (requirementJsonObject.has("character")) {
            numberCharacter = requirementJsonObject.getInt("character");
        }

        if (requirementJsonObject.has("building")) {
            numberBuilding = requirementJsonObject.getInt("building");
        }

        if (requirementJsonObject.has("territory")) {
            numberTerritory = requirementJsonObject.getInt("territory");
        }

        if (requirementJsonObject.has("cost")) {
            SingleCost singleCost = parseCost(requirementJsonObject.getJSONObject("cost"));
            resources = singleCost.getResources();
        }

        Requirement requirement = new Requirement(numberVenture, numberCharacter, numberBuilding, numberTerritory, resources);
        return requirement;
    }

    private void parseImmediateEffect() {
        String jsonContent = null;
        try {
            Scanner scanner = new Scanner(new File("ImmediateEffect.json"));
            jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
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

        JSONArray newPlayHarvestEffects = rootObject.getJSONArray("NewPlayHarvestEffect");
        parseNewPlayHarvestEffect(newPlayHarvestEffects);

        JSONArray productEffects = rootObject.getJSONArray("ProductEffect");
        parseProductEffect(productEffects);

        JSONArray notScoreEffects = rootObject.getJSONArray("NotScoreEffect");
        parseNotScoreEffect(notScoreEffects);
    }

    private void parsePermanentEffect() {
        String jsonContent = null;
        try {
            Scanner scanner = new Scanner(new File("PermanentEffect.json"));
            jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }


        JSONObject rootObject = new JSONObject(jsonContent);

        /*JSONArray plusTowerEffects = rootObject.getJSONArray("PlusTowerEffect");
        parsePlusTowerEffect(plusTowerEffects);

        JSONArray plusProductionEffects = rootObject.getJSONArray("PlusProductionEffect");
        parsePlusProductionEffect(plusProductionEffects);

        JSONArray plusHarvestEffects = rootObject.getJSONArray("PlusHarvestEffect");
        parsePlusHarvestEffect(plusHarvestEffects);*/

        JSONArray plusDiceEffects = rootObject.getJSONArray("PlusDiceEffect");
        parsePlusDiceEffect(plusDiceEffects);

        JSONArray malusOnBenefitEffects = rootObject.getJSONArray("MalusOnBenefitEffect");
        parseMalusOnBenefitEffect(malusOnBenefitEffects);

        JSONArray harvestEffects = rootObject.getJSONArray("HarvestEffect");
        parseHarvestEffect(harvestEffects);

        JSONArray productionEffects = rootObject.getJSONArray("ProductionEffect");
        parseProductionEffect(productionEffects);

        JSONArray endGameEffects = rootObject.getJSONArray("EndGameEffect");
        parseEndGameEffect(endGameEffects);
    }

    private void parseNotScoreEffect(JSONArray notScoreEffects) {
        for (int i = 0; i < notScoreEffects.length(); i++) {
            JSONObject jsonObject = notScoreEffects.getJSONObject(i);

            int idEffect = jsonObject.getInt("id");
            String notScore = jsonObject.getString("not_score");

            NotScoreEffect notScoreEffect =  new NotScoreEffect(notScore);
            effectMap.put(idEffect, notScoreEffect);
        }
    }

    private void parseMalusOnBenefitEffect(JSONArray malusOnBenefitEffects) {
        for (int i = 0; i < malusOnBenefitEffects.length(); i++) {
            JSONObject jsonObject = malusOnBenefitEffects.getJSONObject(i);

            int idEffect = jsonObject.getInt("id");
            String resourceType = jsonObject.getString("resource");
            int malusValue = jsonObject.getInt("malus");

            MalusOnBenefitEffect malusOnBenefitEffect = new MalusOnBenefitEffect(resourceType, malusValue);
            effectMap.put(idEffect, malusOnBenefitEffect);
        }

    }

    private void parseBoardComponent() {
        String jsonContent = null;
        try {
            Scanner scanner = new Scanner(new File("Board.json"));
            jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }


        JSONObject rootObject = new JSONObject(jsonContent);

        JSONArray excommunicationTiles = rootObject.getJSONArray("ExcommunicationTile");
        parseExcommunicationTile(excommunicationTiles);
    }

    private void parseExcommunicationTile(JSONArray excommunicationTiles) {
        for (int i = 0; i < excommunicationTiles.length(); i++) {
            JSONObject jsonObject = excommunicationTiles.getJSONObject(i);
            int idTile = jsonObject.getInt("id");
            int period = jsonObject.getInt("period");
            ArrayList<AbsEffect> permanentEffects = parsingEffect(jsonObject.getJSONArray("permanent_effect"));
            ExcommunicationTile excommunicationTile = new ExcommunicationTile(period, permanentEffects);
            switch (period) {
                case 1:
                    excommunicationTileFirstPeriod.add(excommunicationTile);
                    break;
                case 2:
                    excommunicationTileSecondPeriod.add(excommunicationTile);
                    break;
                case 3:
                    excommunicationTileThirdPeriod.add(excommunicationTile);
                    break;
            }
        }
    }

    private void parsePlusDiceEffect(JSONArray plusDiceEffects) {
        for (int i = 0; i < plusDiceEffects.length(); i++) {
            JSONObject jsonObject = plusDiceEffects.getJSONObject(i);
            int idEffect = jsonObject.getInt("id");
            CardColor cardColor = null;
            int plusDiceValue = jsonObject.getInt("plus_dice_value");
            String type = jsonObject.getString("position");
            ArrayList<SingleCost> sales = new ArrayList<>();
            if (jsonObject.has("sale")) {
                JSONArray salesJsonArray = jsonObject.getJSONArray("sale");
                for (int j = 0; j < salesJsonArray.length(); j++) {
                    sales.add(parseCost(salesJsonArray.getJSONObject(j)));
                }
            } else {
                sales.add(new SingleCost(new ResourcesSet(0, 0, 0, 0, 0,0,0)));
            }
            if (jsonObject.has("card_color")) {
                cardColor = Util.parseCardColor(jsonObject.getString("card_color"));
            }

            PlusDiceEffect plusDiceEffect =  new PlusDiceEffect(plusDiceValue, type, cardColor, sales);
            effectMap.put(idEffect, plusDiceEffect);

        }
    }

    private void parseEndGameEffect(JSONArray endGameEffects) {
        for (int i = 0; i < endGameEffects.length(); i++) {
            int idEffect = endGameEffects.getJSONObject(i).getInt("id");
            //TODO
            /*ArrayList<AbsEffect> effects = parsingEffect(endGameEffects.getJSONObject(i).getJSONArray("immediate_effect"));

            EndGameEffect endGameEffect = new EndGameEffect(effects);
            effectMap.put(idEffect, endGameEffect);*/
        }
    }

    private ArrayList<AbsEffect> parsingEffect(JSONArray jsonArray) {
        ArrayList<AbsEffect> arrayList = new ArrayList<>();
        for (int y = 0; y < jsonArray.length() ; y++) {

            int id = jsonArray.getJSONObject(y).getInt("effect");
            arrayList.add(effectMap.get(id));
        }

        return arrayList;
    }

    private void parseProductionEffect(JSONArray productionEffects) {
        for (int i = 0; i < productionEffects.length(); i++) {
            JSONObject productionEffectObject = productionEffects.getJSONObject(i);
            int idEffect = productionEffectObject.getInt("id");
            int productionValue = productionEffectObject.getInt("production_value");
            ArrayList<AbsEffect> immediateEffects = parsingEffect(productionEffectObject.getJSONArray("immediate_effect"));

            ProductionEffect productionEffect = new ProductionEffect(productionValue, immediateEffects);
            effectMap.put(idEffect, productionEffect);

        }
    }

    private void parseHarvestEffect(JSONArray harvestEffects) {
        for (int i = 0; i < harvestEffects.length(); i++) {
            JSONObject harvestEffectObject = harvestEffects.getJSONObject(i);
            int idEffect = harvestEffectObject.getInt("id");
            int harvestValue = harvestEffectObject.getInt("harvest_value");
            ArrayList<AbsEffect> immediateEffects = parsingEffect(harvestEffectObject.getJSONArray("immediate_effect"));

            HarvestEffect harvestEffect = new HarvestEffect(harvestValue, immediateEffects);
            effectMap.put(idEffect, harvestEffect);

        }
    }

    /*private void parsePlusHarvestEffect(JSONArray plusHarvestEffects) {
        for (int i = 0; i < plusHarvestEffects.length(); i++) {
            int idEffect =  plusHarvestEffects.getJSONObject(i).getInt("id");
            int diceValue = plusHarvestEffects.getJSONObject(i).getInt("plus_dice_value");
            PlusHarvestEffect plusHarvestEffect = new PlusHarvestEffect(diceValue);
            effectMap.put(idEffect, plusHarvestEffect);
        }
    }

    private void parsePlusProductionEffect(JSONArray plusProductionEffects) {
        for (int i = 0; i < plusProductionEffects.length(); i++) {
            int idEffect =  plusProductionEffects.getJSONObject(i).getInt("id");
            int diceValue = plusProductionEffects.getJSONObject(i).getInt("plus_dice_value");
            PlusProductionEffect plusProductionEffect = new PlusProductionEffect(diceValue);
            effectMap.put(idEffect, plusProductionEffect);
        }
    }

    private void parsePlusTowerEffect(JSONArray plusTowerEffects) {
        for (int i = 0; i < plusTowerEffects.length(); i++) {
            int idEffect = plusTowerEffects.getJSONObject(i).getInt("id");
            CardColor cardColor = Util.parseCardColor(plusTowerEffects.getJSONObject(i).getString("card_color"));
            int plusDiceValue = plusTowerEffects.getJSONObject(i).getInt("plus_dice_value");
            ArrayList<SingleCost> sales = new ArrayList<>();
            if (plusTowerEffects.getJSONObject(i).has("sale")) {
                JSONArray salesJsonArray = plusTowerEffects.getJSONObject(i).getJSONArray("sale");
                for (int j = 0; j < salesJsonArray.length(); j++) {
                    sales.add(parseCost(salesJsonArray.getJSONObject(j)));
                }
            }
            PlusTowerEffect plusTowerEffect = new PlusTowerEffect(cardColor, plusDiceValue, sales);
            effectMap.put(idEffect, plusTowerEffect);
        }
    }*/

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

        if (jsonObject.has("required")) {
            SingleCost requiredCost = parseCost(jsonObject.getJSONArray("required").getJSONObject(0));
            SingleCost payedCost = parseCost(jsonObject.getJSONArray("payed").getJSONObject(0));
            MilitaryCost militaryCost = new MilitaryCost(payedCost.getResources(), requiredCost.getResources());
            return militaryCost;
        }


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
        return new ArrayList<CharacterCard>((ArrayList<? extends CharacterCard>) Util.shuffleCard(characterCardArrayList));
    }

    public ArrayList<TerritoryCard> getTerritoryCardArrayList() {

        return new ArrayList<TerritoryCard>((ArrayList<? extends TerritoryCard>) Util.shuffleCard(territoryCardArrayList));
    }

    public ArrayList<VentureCard> getVentureCardArrayList() {
        return new ArrayList<VentureCard>((ArrayList<? extends VentureCard>) Util.shuffleCard(ventureCardArrayList));
    }

    public ArrayList<BuildingCard> getBuildingCardArrayList() {
        return new ArrayList<BuildingCard>((ArrayList<? extends BuildingCard>) Util.shuffleCard(buildingCardArrayList));
    }

    public ExcommunicationTile getExcommunicationTileFirstPeriod() {
        Collections.shuffle(excommunicationTileFirstPeriod, new Random());
        return excommunicationTileFirstPeriod.get(0);
    }

    public ExcommunicationTile getExcommunicationTileSecondPeriod() {
        Collections.shuffle(excommunicationTileSecondPeriod, new Random());
        return excommunicationTileSecondPeriod.get(0);
    }

    public ExcommunicationTile getExcommunicationTileThirdPeriod() {
        Collections.shuffle(excommunicationTileThirdPeriod, new Random());
        return excommunicationTileThirdPeriod.get(0);
    }

    public AbsEffect[] getTowerTerritoryEffect() {
        AbsEffect[] absEffects = new AbsEffect[4];
        absEffects[0] = effectMap.get(0);
        absEffects[1] = effectMap.get(0);
        absEffects[2] = effectMap.get(23);
        absEffects[3] = effectMap.get(24);
        return absEffects;
    }

    public AbsEffect[] getTowerCharacterEffect() {
        AbsEffect[] absEffects = new AbsEffect[4];
        absEffects[0] = effectMap.get(0);
        absEffects[1] = effectMap.get(0);
        absEffects[2] = effectMap.get(33);
        absEffects[3] = effectMap.get(34);
        return absEffects;
    }
    public AbsEffect[] getTowerBuildingEffect() {
        AbsEffect[] absEffects = new AbsEffect[4];
        absEffects[0] = effectMap.get(0);
        absEffects[1] = effectMap.get(0);
        absEffects[2] = effectMap.get(43);
        absEffects[3] = effectMap.get(44);
        return absEffects;
    }
    public AbsEffect[] getTowerVentureEffect() {
        AbsEffect[] absEffects = new AbsEffect[4];
        absEffects[0] = effectMap.get(0);
        absEffects[1] = effectMap.get(0);
        absEffects[2] = effectMap.get(53);
        absEffects[3] = effectMap.get(54);
        return absEffects;
    }

    // METODO PER TESTING
    public PermanentEffect getPermanentEffect() {
        return (PermanentEffect) effectMap.get(492);
    }
}
