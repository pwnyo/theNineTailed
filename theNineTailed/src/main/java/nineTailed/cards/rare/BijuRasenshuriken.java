package nineTailed.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Tail;
import nineTailed.patches.IOrbListener;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class BijuRasenshuriken extends AbstractDynamicCard implements IOrbListener {
    public final static String ID = makeID(BijuRasenshuriken.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 9;

    public BijuRasenshuriken() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 2;
        baseMagicNumber = magicNumber = 9;
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null &&
                AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMPLETE && AbstractDungeon.player != null) {
            configureCostsOnNewCard();
        }
    }

    void configureCostsOnNewCard() {
        int count = 0;
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Tail) {
                count++;
            }
        }
        this.updateCost(-count);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            initializeDescription();
        }
    }

    @Override
    public void onChannel(AbstractOrb o) {
        if (o instanceof Tail) {
            updateCost(-1);
        }
    }

    @Override
    public void onEvoke(AbstractOrb o) {

    }

    @Override
    public void onGainOrbSlot() {

    }

    @Override
    public void onLoseOrbSlot() {

    }
}