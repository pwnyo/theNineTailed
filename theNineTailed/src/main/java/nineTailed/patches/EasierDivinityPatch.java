package nineTailed.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.stances.DivinityStance;
import nineTailed.relics.commoner.PaperFlowers;

@SpirePatch(clz = MantraPower.class, method = "stackPower")
public class EasierDivinityPatch {
    @SpireInsertPatch(loc=41)
    public static SpireReturn<Void> Insert(MantraPower __mantraPower, int stackAmount) {
        if (!AbstractDungeon.player.hasRelic(PaperFlowers.ID)) {
            return SpireReturn.Continue();
        }
        if (__mantraPower.amount >= 8) {
            AbstractDungeon.actionManager.addToTop(new ChangeStanceAction(DivinityStance.STANCE_ID));
            __mantraPower.amount -= 8;
        }
        if (__mantraPower.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(__mantraPower.owner, __mantraPower.owner, MantraPower.POWER_ID));
        }
        return SpireReturn.Return();
    }
}
