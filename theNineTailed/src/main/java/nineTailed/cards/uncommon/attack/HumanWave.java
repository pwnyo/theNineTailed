package nineTailed.cards.uncommon.attack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.HumanWaveAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class HumanWave extends AbstractDynamicCard {
    public final static String ID = makeID(HumanWave.class.getSimpleName());
    public static final String IMG = makeCardPath("HumanWave.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = -1;

    public HumanWave() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 4;
        isMultiDamage = true;
        baseBlock = block = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HumanWaveAction(multiDamage, block, damageTypeForTurn, freeToPlayOnce, energyOnUse));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            initializeDescription();
        }
    }
}