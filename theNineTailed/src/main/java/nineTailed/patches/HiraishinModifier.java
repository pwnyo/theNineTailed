package nineTailed.patches;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

@AbstractCardModifier.SaveIgnore
public class HiraishinModifier extends AbstractCardModifier {
    public static final String ID = NarutoMod.makeID("Hiraishin");
    private static final Texture ICON = TextureLoader.getTexture(NarutoMod.makeUIPath("hiraishin_mark.png"));

    public HiraishinModifier() {

    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new HiraishinModifier();
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(ICON).render(card);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, HiraishinModifier.ID);
    }
}
