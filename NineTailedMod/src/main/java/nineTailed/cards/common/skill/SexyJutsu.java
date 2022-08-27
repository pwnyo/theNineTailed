package nineTailed.cards.common.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import nineTailed.actions.SexyJutsuAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class SexyJutsu extends AbstractDynamicCard {
    public final static String ID = makeID(SexyJutsu.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public SexyJutsu() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 2;
        baseMagicNumber2 = magicNumber2 = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        addToBot(new SexyJutsuAction(m, magicNumber));
    }

    public void triggerOnGlowCheck() {
        boolean check = (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() &&
                ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == CardType.ATTACK);
        glow(check);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeMagic2(3);
            initializeDescription();
        }
    }
}