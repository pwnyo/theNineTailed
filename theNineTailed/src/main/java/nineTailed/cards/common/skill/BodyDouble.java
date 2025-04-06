package nineTailed.cards.common.skill;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.BodyDoubleAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class BodyDouble extends AbstractDynamicCard {
    public final static String ID = makeID(BodyDouble.class.getSimpleName());
    public static final String IMG = makeCardPath("BodyDouble.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public BodyDouble() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        block();
        addToBot(new BodyDoubleAction(1));
    }

    public void triggerOnGlowCheck() {
        glow(!AbstractDungeon.player.hasEmptyOrb());
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            initializeDescription();
        }
    }
}