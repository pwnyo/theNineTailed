package nineTailed.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Pray;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.PrayerWheel;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makePowerPath;

public class NinshuPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = NarutoMod.makeID("NinshuPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    private int hpLoss;

    public NinshuPower(final AbstractCreature owner, final int hpLoss, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.hpLoss = hpLoss;
        this.amount = amount;
        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new LoseHPAction(owner, owner, hpLoss));
        for (int i = 0; i < amount; i++) {
            addToBot(new MakeTempCardInDrawPileAction(getAnyColorPower(), 1, true, true));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + hpLoss + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
        else {
            description = DESCRIPTIONS[0] + hpLoss + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }
    }

    public static AbstractCard getAnyColorPower() {
        int roll = AbstractDungeon.cardRandomRng.random(99);
        AbstractCard.CardRarity cardRarity;
        if (roll < 85) {
            cardRarity = AbstractCard.CardRarity.UNCOMMON;
        } else {
            cardRarity = AbstractCard.CardRarity.RARE;
        }
        return CardLibrary.getAnyColorCard(AbstractCard.CardType.POWER, cardRarity).makeCopy();
    }
}
