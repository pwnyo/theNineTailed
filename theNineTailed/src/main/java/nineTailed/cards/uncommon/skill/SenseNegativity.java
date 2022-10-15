package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.blue.GoForTheEyes;
import com.megacrit.cardcrawl.cards.purple.FearNoEvil;
import com.megacrit.cardcrawl.cards.red.SpotWeakness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class SenseNegativity extends AbstractDynamicCard {
    public final static String ID = makeID(SenseNegativity.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public SenseNegativity() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && m.getIntentBaseDmg() >= 0) {
            addToBot(new ApplyPowerAction(p, p, new MantraPower(p, 3)));
            addToBot(new DrawCardAction(magicNumber));
        }
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