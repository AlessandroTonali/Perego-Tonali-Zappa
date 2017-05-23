package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class TowerSpace extends ActionSpace {
    private Card card;
    private BenefitsEffect benefitsEffect;

    public TowerSpace(Card card, BenefitsEffect effect, int value) {
        super(value);
        this.card = card;
        this.benefitsEffect = effect;

    }

    public Card getCard() {
        return card;
    }


    public BenefitsEffect getBenefitsEffect() {
        return benefitsEffect;
    }
}
