package nineTailed.relics.boss;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.NarutoMod;
import nineTailed.cards.temp.sasukeCollab.JetBlackGale;
import nineTailed.cards.temp.sasukeCollab.MagnetRasengan;
import nineTailed.cards.temp.sasukeCollab.MajesticAttire;
import nineTailed.util.TextureLoader;

import java.util.ArrayList;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class RightHand extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(RightHand.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("right_hand.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("right_hand.png"));

    public RightHand() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
    }

    public void onEquip() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.add(new MagnetRasengan());
        cards.add(new JetBlackGale());
        cards.add(new MajesticAttire());

        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.cardRewardScreen.open(cards, null, DESCRIPTIONS[1]);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
