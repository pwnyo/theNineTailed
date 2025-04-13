package nineTailed.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import nineTailed.NarutoMod;
import nineTailed.orbs.Tail;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makePowerPath;

public class AsuraForm2Power extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = NarutoMod.makeID("AsuraForm2Power");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("asuraform84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("asuraform32.png"));

    public AsuraForm2Power(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (amount == 1) {
            amount++;
        }
        updateDescription();
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (orb instanceof Tail && !((Tail) orb).isBonus) {
            for (int i = 0; i < amount - 1; i++) {
                Tail bonusTail = (Tail)orb.makeCopy();
                bonusTail.isBonus = true;
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(bonusTail));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
