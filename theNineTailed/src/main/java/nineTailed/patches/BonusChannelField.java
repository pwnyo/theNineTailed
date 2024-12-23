package nineTailed.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

@SpirePatch(
        clz= AbstractOrb.class,
        method=SpirePatch.CLASS
)
public class BonusChannelField {
    public static SpireField<Boolean> isBonus = new SpireField<>(() -> false);
}
