package nineTailed.cards.common.skill;

import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class NeverGiveUp extends AbstractDynamicCard {
    public final static String ID = makeID(NeverGiveUp.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public NeverGiveUp() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = 8;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
    }

    @Override
    protected void applyPowersToBlock() {
        if (AbstractDungeon.player.isBloodied) {
            baseBlock += magicNumber;
        }
        super.applyPowersToBlock();
        if (AbstractDungeon.player.isBloodied) {
            baseBlock -= magicNumber;
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glow(AbstractDungeon.player.isBloodied);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}