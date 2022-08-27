package nineTailed.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import nineTailed.cards.placeholder.AbstractDefaultCard;

import static nineTailed.NarutoMod.makeID;

public class DefaultSecondMagicNumber extends DynamicVariable {
    
    @Override
    public String key() {
        return makeID("m2");
    }
    
    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractDefaultCard) card).isMagic2Modified;
    }
    
    @Override
    public int value(AbstractCard card) {
        return ((AbstractDefaultCard) card).magicNumber2;
    }
    
    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractDefaultCard) card).baseMagicNumber2;
    }
    
    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDefaultCard) card).upgradedMagicNumber2;
    }
}