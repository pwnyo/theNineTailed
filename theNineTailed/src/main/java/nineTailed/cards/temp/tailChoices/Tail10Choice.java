package nineTailed.cards.temp.tailChoices;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.orbs.Tail;
import nineTailed.orbs.biju.Tail10;
import nineTailed.orbs.biju.Tail8;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

@AutoAdd.Ignore
public class Tail10Choice extends AbstractDynamicCard {
    public final static String ID = makeID(Tail10Choice.class.getSimpleName());
    public static final String IMG = makeCardPath("Tail10Choice.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;

    public Tail10Choice() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onChoseThisOption() {
        addToBot(new ChannelAction(new Tail10()));
    }

    @Override
    public void upgrade() {
    }
}