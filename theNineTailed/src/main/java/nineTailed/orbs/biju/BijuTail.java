package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nineTailed.orbs.Tail;
import nineTailed.powers.ChakraPower;

import java.util.ArrayList;

public abstract class BijuTail extends Tail {
    AbstractPlayer p;
    public BijuTail(String orbId, String imgPath, int passive, int evoke) {
        super(orbId, imgPath, passive, evoke);
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ChakraPower(p, passiveAmount)));
        AbstractDungeon.actionManager.addToTop(new LightningOrbEvokeAction(new DamageInfo(p, evokeAmount, DamageInfo.DamageType.THORNS), false));
    }

    protected void gainPower(AbstractPlayer p, AbstractPower pow) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, pow));
    }
    public static BijuTail getRandomBijuTail() {
        ArrayList<BijuTail> orbs = new ArrayList();
        orbs.add(new Tail1());
        orbs.add(new Tail2());
        orbs.add(new Tail3());
        orbs.add(new Tail4());
        orbs.add(new Tail5());
        orbs.add(new Tail6());
        orbs.add(new Tail7());
        orbs.add(new Tail8());
        return orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1));
    }
}
