package nineTailed.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.DivinityStance;
import nineTailed.powers.NoEnergyOnStanceChangePower;

public class NoEnergyOnStanceChangePatch {
    @SpirePatch(clz = CalmStance.class, method = "onExitStance")
    public static class CalmChange {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(CalmStance __stance) {
            if (AbstractDungeon.player.hasPower(NoEnergyOnStanceChangePower.POWER_ID)) {
                __stance.stopIdleSfx();
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch(clz = DivinityStance.class, method = "onEnterStance")
    public static class DivinityChange {
        @SpireInsertPatch(loc=76)
        public static SpireReturn<Void> Insert(DivinityStance __stance) {
            if (AbstractDungeon.player.hasPower(NoEnergyOnStanceChangePower.POWER_ID)) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
