package nineTailed.cards.uncommon.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.powers.AmphibiousModePower;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class AmphibiousMode extends AbstractDynamicCard {
    public final static String ID = makeID(AmphibiousMode.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 2;

    public AmphibiousMode() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AmphibiousModePower(p)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}