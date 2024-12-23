package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class SummonToaduo extends AbstractDynamicCard {
    public final static String ID = makeID(SummonToaduo.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 2;

    public SummonToaduo() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int roll = AbstractDungeon.cardRandomRng.random(99);
        CardRarity cardRarity;
        if (roll < 85) {
            cardRarity = CardRarity.UNCOMMON;
        } else {
            cardRarity = CardRarity.RARE;
        }
        AbstractCard one = CardLibrary.getAnyColorCard(CardType.POWER, cardRarity);
        AbstractCard two = CardLibrary.getAnyColorCard(CardType.POWER, cardRarity);
        addToBot(new MakeTempCardInHandAction(one, true));
        addToBot(new MakeTempCardInHandAction(two, true));
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