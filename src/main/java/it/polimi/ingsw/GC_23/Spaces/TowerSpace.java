package it.polimi.ingsw.GC_23.Spaces;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Effects.PermanentEffect;
import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class TowerSpace extends ActionSpace {
    private Card card;
    private final BenefitsEffect benefitsEffect; //bonus immediato

    public TowerSpace(Card card, BenefitsEffect effect, int value) {
        super(value);
        this.card = card;
        this.benefitsEffect = effect;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }


    public BenefitsEffect getBenefitsEffect() {
        return benefitsEffect;
    }

    public void resetCard(){
        this.card= null;
    }

    @Override
    public String toString() {
        if(super.getFamilyMember() == null){
            return "value: "+ super.getValue() + "\neffect: " +
                    this.benefitsEffect.toString() + "\n" + this.card.toString()
                    + "\n no player inside";
        }
        return "value: "+ super.getValue() + "\neffect: " +
                this.benefitsEffect.toString() + "\n" + this.card.toString() + "\n family member "
                + super.getFamilyMember().toString();

    }

    @Override
    public void checkBeforeActivablePermanentEffect(FamilyMember familyMember) throws RemoteException {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();
        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof PlusDiceEffect && ((PlusDiceEffect) permanentEffect).getType().equals("tower")) {
                ResourcesSet sale = ((PlusDiceEffect) permanentEffect).chooseSale(familyMember.getPlayer()).getResources();
                ResourcesSet costSelected = card.chooseCost(familyMember.getPlayer()).getResources();
                costSelected.sum(sale, familyMember.getPlayer());
                card.setCostSelected(new SingleCost(costSelected));
            }
        }
    }

    @Override
    public void checkAfterActivablePermanentEffect(FamilyMember familyMember) {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();
        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            //TODO
        }
    }
}
