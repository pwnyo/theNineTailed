package nineTailed.cards.temp.sasukeCollab;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Tail;
import nineTailed.patches.IOrbListener;

import java.awt.*;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class MajesticAttire extends AbstractDynamicCard implements IOrbListener {
    public final static String ID = makeID(MajesticAttire.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 4;

    public MajesticAttire() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
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
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
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
}