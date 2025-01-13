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
import nineTailed.patches.CustomTags;
import nineTailed.patches.IOrbListener;

import java.util.ArrayList;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class BijuRasenshuriken extends AbstractDynamicCard implements IOrbListener {
    public final static String ID = makeID(BijuRasenshuriken.class.getSimpleName());
    public static final String IMG = makeCardPath("BijuRasenshuriken.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 6;

    public BijuRasenshuriken() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 2;
        baseMagicNumber = magicNumber = 9;
        tags.add(CustomTags.RASEN);
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
        ArrayList<AbstractGameAction.AttackEffect> effects = getBijuAttackEffects();
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage), effects.get(i)));
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

    @Override
    public void onRemoveOrb() {

    }
    public ArrayList<AbstractGameAction.AttackEffect> getBijuAttackEffects() {
        ArrayList<AbstractGameAction.AttackEffect> effects = new ArrayList<>();
        effects.add(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        effects.add(AbstractGameAction.AttackEffect.FIRE);
        effects.add(AbstractGameAction.AttackEffect.SHIELD);
        effects.add(AbstractGameAction.AttackEffect.FIRE);
        effects.add(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        effects.add(AbstractGameAction.AttackEffect.POISON);
        effects.add(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        effects.add(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        effects.add(AbstractGameAction.AttackEffect.SMASH);
        return effects;
    }
}