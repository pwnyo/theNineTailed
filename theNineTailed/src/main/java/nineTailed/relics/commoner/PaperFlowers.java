package nineTailed.relics.commoner;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class PaperFlowers extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(PaperFlowers.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    private static final int AMOUNT = 8;

    public PaperFlowers() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + AMOUNT + this.DESCRIPTIONS[1];
    }
}
