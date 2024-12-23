package nineTailed.relics.boss;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.NarutoMod;
import nineTailed.orbs.Tail;
import nineTailed.relics.commoner.SealedScroll;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class SpiralScroll extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(SpiralScroll.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public SpiralScroll() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStart() {
        addToBot(new ChannelAction(new Tail()));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(SealedScroll.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(SealedScroll.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(SealedScroll.ID);
    }
}
