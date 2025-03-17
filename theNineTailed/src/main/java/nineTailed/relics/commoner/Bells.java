package nineTailed.relics.commoner;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class Bells extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(Bells.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("bells.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("bells.png"));

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
            CardCrawlGame.sound.play("TINGSHA");
        }
    }
    public void update() {
        super.update();// 39
        if (this.hb.hovered && InputHelper.justClickedLeft) {
            CardCrawlGame.sound.playA("TINGSHA", MathUtils.random(-0.2F, 0.1F));
            this.flash();
        }
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 3 + this.DESCRIPTIONS[1];
    }
}
