package nineTailed.cards.uncommon.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Chill;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Clone;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class Stampede extends AbstractDynamicCard {
    public final static String ID = makeID(Stampede.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public Stampede() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 10;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            addToBot(new ChannelAction(new Clone()));
        }
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }
}