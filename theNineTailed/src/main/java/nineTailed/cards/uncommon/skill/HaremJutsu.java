package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.HaremAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Clone;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class HaremJutsu extends AbstractDynamicCard {
    public final static String ID = makeID(HaremJutsu.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 2;

    public HaremJutsu() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new ChannelAction(new Clone()));
        }
        addToBot(new HaremAction(1));
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