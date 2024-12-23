package nineTailed.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.cards.basic.AbstractRasengan;

@SpirePatch(clz = CardGroup.class, method = "addToHand")
@SpirePatch(clz = CardGroup.class, method = "addToTop")
@SpirePatch(clz = CardGroup.class, method = "addToBottom")
@SpirePatch(clz = CardGroup.class, method = "addToRandomSpot")
public class TriggerRasenOnAddToHandPatch {
    @SpirePostfixPatch
    public static void trigger(CardGroup __instance, AbstractCard c) {
        if (AbstractDungeon.player != null && __instance == AbstractDungeon.player.hand && c instanceof AbstractRasengan) {
            ((AbstractRasengan)c).triggerWhenAddedToHand();
        }
    }
}
