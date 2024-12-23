package nineTailed.patches;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageUpMod extends AbstractCardModifier //borrowed from JediMod :)
{
    public int amount;

    public DamageUpMod(int amt)
    {
        amount = amt;
    }

    @Override
    public boolean isInherent(AbstractCard card)
    {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        amount = 0;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target)
    {
        return damage + amount;
    }

    @Override
    public AbstractCardModifier makeCopy()
    {
        return new DamageUpMod(amount);
    }
}