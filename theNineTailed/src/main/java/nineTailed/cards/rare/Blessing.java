package nineTailed.cards.rare;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.DivinityStance;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class Blessing extends AbstractDynamicCard {
    public final static String ID = makeID(Blessing.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 3;

    public Blessing() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(DivinityStance.STANCE_ID));
        addToBot(new LoseEnergyAction(3));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}