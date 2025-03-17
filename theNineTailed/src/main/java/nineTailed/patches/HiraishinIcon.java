package nineTailed.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import nineTailed.NarutoMod;
import nineTailed.util.TextureLoader;

public class HiraishinIcon extends AbstractCustomIcon {
    public static final String ID = NarutoMod.makeID("HiraishinIcon");
    public HiraishinIcon(String name, Texture texture) {
        super(name, TextureLoader.getTexture((NarutoMod.makeUIPath("hiraishin_mark.png"))));
    }
}
