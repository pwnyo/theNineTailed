package nineTailed.cards.temp;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Eviscerate;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.actions.FlyingRaijinAction;
import nineTailed.cards.AbstractDynamicCard;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class FlyingRaijin extends AbstractDynamicCard {
    public final static String ID = makeID(FlyingRaijin.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;
    private AbstractCard markedCard;

    public FlyingRaijin() {
        this(null);
    }
    public FlyingRaijin(AbstractCard marked) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 8;
        showMarkedCard(marked);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (markedCard != null) {
            addToBot(new FlyingRaijinAction(markedCard));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            showMarkedCard();
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        FlyingRaijin copy = (FlyingRaijin)super.makeStatEquivalentCopy();
        copy.showMarkedCard(this.markedCard);
        return copy;
    }

    void showMarkedCard(AbstractCard marked) {
        if (marked != null) {
            markedCard = marked;
            cardsToPreview = marked.makeStatEquivalentCopy();
        }
        showMarkedCard();
    }
    void showMarkedCard() {
        if (markedCard != null) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + markedCard.name + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        else {
            rawDescription = cardStrings.DESCRIPTION;
        }
        initializeDescription();
    }
}