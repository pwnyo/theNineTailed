package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.RasenFromDeckToHandAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.patches.CustomTags;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class RasenAbsorption extends AbstractDynamicCard {
    public final static String ID = makeID(RasenAbsorption.class.getSimpleName());
    public static final String IMG = makeCardPath("RasenAbsorption.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 0;

    public RasenAbsorption() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.RASEN);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RasenFromDeckToHandAction(magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (canUse) {
            for (AbstractCard c : p.drawPile.group) {
                if (c.hasTag(CustomTags.RASEN)) {
                    return true;
                }
            }

            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        }
        return false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}