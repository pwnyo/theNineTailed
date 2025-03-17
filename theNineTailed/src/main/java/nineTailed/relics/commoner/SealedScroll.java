package nineTailed.relics.commoner;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.NarutoMod;
import nineTailed.orbs.Tail;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;


public class SealedScroll extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(SealedScroll.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("sealed_scroll.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("sealed_scroll.png"));

    public SealedScroll() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Tail());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
