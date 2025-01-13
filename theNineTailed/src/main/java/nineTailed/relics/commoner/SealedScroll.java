package nineTailed.relics.commoner;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import nineTailed.NarutoMod;
import nineTailed.orbs.Tail;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.*;
import static nineTailed.NarutoMod.makeID;


public class SealedScroll extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(SealedScroll.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

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
