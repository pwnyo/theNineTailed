package nineTailed.relics.commoner;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.NarutoMod;
import nineTailed.orbs.Tail;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class CrystalNecklace extends CustomRelic implements OnChannelRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(CrystalNecklace.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("crystal_necklace.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("crystal_necklace.png"));
    private static final int AMT = 6;

    public CrystalNecklace() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public void onChannel(AbstractOrb abstractOrb) {
        if (abstractOrb instanceof Tail) {
            counter++;
            if (counter % AMT == 0) {
                counter = 0;
                flash();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new IncreaseMaxOrbAction(1));
            }
        }
    }
}
