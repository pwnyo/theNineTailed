package nineTailed.relics.boss;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class Habanero extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(Habanero.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public Habanero() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(19, true);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 19 + LocalizedStrings.PERIOD;
    }
}
