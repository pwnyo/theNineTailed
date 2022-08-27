package nineTailed.cards.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Clone;
import nineTailed.patches.IOrbListener;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class Rasengan extends AbstractDynamicCard implements IOrbListener {
    public final static String ID = makeID(Rasengan.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 3;

    public Rasengan() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        countOrbs();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
    void countOrbs() {
        if (CardCrawlGame.dungeon == null || AbstractDungeon.currMapNode == null ||
                AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            return;
        }
        int count = 0;
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof Clone)
                count++;
        }
        setCostForTurn(cost - count);
    }
    @Override
    public void onChannel(AbstractOrb o) {
        countOrbs();
    }

    @Override
    public void onEvoke(AbstractOrb o) {
        countOrbs();
    }

    @Override
    public void onGainOrbSlot() {
        countOrbs();
    }

    @Override
    public void onLoseOrbSlot() {
        countOrbs();
    }
}