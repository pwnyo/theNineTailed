package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class ScoutFormation extends AbstractDynamicCard {
    public final static String ID = makeID(ScoutFormation.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public ScoutFormation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        addToBot(new DrawCardAction(2));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            initializeDescription();
        }
    }
}