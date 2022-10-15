package nineTailed.cards.common.attack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.TailWhipAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class TailWhip extends AbstractDynamicCard {
    public final static String ID = makeID(TailWhip.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public TailWhip() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TailWhipAction(damage, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }
}