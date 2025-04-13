package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;


public class Tail6 extends BijuTail {
    public Tail6() {
        super(1,"Tail6", "tail6");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, passiveAmount)));
            }
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail6();
    }
}
