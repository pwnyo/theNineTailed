package nineTailed.cards.rare;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.actions.UzumakiBarrageAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Clone;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

@AutoAdd.Ignore
public class UzumakiBarrage extends AbstractDynamicCard {
    public final static String ID = makeID(UzumakiBarrage.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 2;

    public UzumakiBarrage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 0;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        countClones();
        for (int i = 0; i < magicNumber; i++) {
            addToTop(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        countClones();
        if (baseMagicNumber > 0) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (baseMagicNumber > 0) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    void countClones() {
        int count = 0;
        baseMagicNumber = magicNumber = 0;
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Clone)
                count++;
            baseMagicNumber = magicNumber = count;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }
}