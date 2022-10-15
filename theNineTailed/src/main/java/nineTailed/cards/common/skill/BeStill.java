package nineTailed.cards.common.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.CalmStance;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.powers.NextTurnMantraPower;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class BeStill extends AbstractDynamicCard {
    public final static String ID = makeID(BeStill.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public BeStill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(CalmStance.STANCE_ID));
        addToBot(new ApplyPowerAction(p, p, new NextTurnMantraPower(p, magicNumber)));
        if (!upgraded) {
            addToBot(new PressEndTurnButtonAction());
        }
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