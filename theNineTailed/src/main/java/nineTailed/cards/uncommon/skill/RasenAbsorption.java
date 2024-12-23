package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.RasenFromDeckToHandAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.patches.CustomTags;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class RasenAbsorption extends AbstractDynamicCard {
    public final static String ID = makeID(RasenAbsorption.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public RasenAbsorption() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.RASEN);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RasenFromDeckToHandAction(magicNumber));
    }

    @Override
    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;

        int rasenCount = 0;
        for (AbstractCard c : p.hand.group)
        {
            if (isRasen(c))
            {
                rasenCount++;
            }
        }
        for (AbstractCard c : p.drawPile.group)
        {
            if (isRasen(c))
            {
                rasenCount++;
            }
        }
        for (AbstractCard c : p.discardPile.group)
        {
            if (isRasen(c))
            {
                rasenCount++;
            }
        }

        if (rasenCount > 0) {
            magicNumber = rasenCount + (upgraded ? 1 : 0);
            super.applyPowers();
            this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) +
                    (rasenCount == 1 ? cardStrings.EXTENDED_DESCRIPTION[0] : cardStrings.EXTENDED_DESCRIPTION[1]);
            this.initializeDescription();
        }

    }

    public void onMoveToDiscard() {
        this.rawDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public static boolean isRasen(AbstractCard c)
    {
        return c.hasTag(CustomTags.RASEN);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}