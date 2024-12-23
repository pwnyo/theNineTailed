package nineTailed.relics.commoner;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class Bells extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(Bells.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public Bells() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
        counter = 0;
    }

    @Override
    public void onManualDiscard() {
        ++this.counter;
        if (this.counter % 3 == 0) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(1));
        }
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 3 + this.DESCRIPTIONS[1];
    }
}
