package nineTailed.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nineTailed.NarutoMod;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Tail;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makePowerPath;

public class BijuTailPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = NarutoMod.makeID("BijuTailPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gathering84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gathering32.png"));

    public BijuTailPower(final AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        if (amount < 1) {
            amount = 1;
        }

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (!(orb instanceof Tail)) {
            return;
        }
        Tail tail = (Tail)orb;
        if (tail.isBijuable) {
            flash();
            amount++;
            if (amount > 10) {
                amount = 1;
            }
            tail.isBijuable = false;
            updateDescription();
        }
    }

    @Override
    public void onInitialApplication() {
        if (AbstractDungeon.player instanceof NineTailed) {
            ((NineTailed) AbstractDungeon.player).recheckAnimation();
        }
    }

    @Override
    public void onRemove() {
        if (AbstractDungeon.player instanceof NineTailed) {
            ((NineTailed) AbstractDungeon.player).recheckAnimation();
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
