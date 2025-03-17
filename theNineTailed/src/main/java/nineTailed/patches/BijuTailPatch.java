package nineTailed.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.orbs.Tail;
import nineTailed.orbs.biju.*;
import nineTailed.powers.BijuTailPower;

@SpirePatch(clz = AbstractPlayer.class, method = "channelOrb")
public class BijuTailPatch {
    @SpirePrefixPatch
    public static void replaceOrb(AbstractPlayer __player, @ByRef AbstractOrb[] orb) {
        AbstractOrb o = orb[0];
        if (__player.hasPower(BijuTailPower.POWER_ID) &&
                o != null && o.ID.equals(Tail.ORB_ID) && ((Tail) o).isBijuable) {
            int nextTail = __player.getPower(BijuTailPower.POWER_ID).amount;
            switch (nextTail) {
                case 1:
                    orb[0] = new Tail1(); break;
                case 2:
                    orb[0] = new Tail2(); break;
                case 3:
                    orb[0] = new Tail3(); break;
                case 4:
                    orb[0] = new Tail4(); break;
                case 5:
                    orb[0] = new Tail5(); break;
                case 6:
                    orb[0] = new Tail6(); break;
                case 7:
                    orb[0] = new Tail7(); break;
                case 8:
                    orb[0] = new Tail8(); break;
                case 9:
                    orb[0] = new Tail(); break;
            }
            ((Tail)orb[0]).isBijuable = true;
        }
    }
}
