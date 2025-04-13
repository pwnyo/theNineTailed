package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;


public class Tail8 extends BijuTail {
    public Tail8() {
        super(1,"Tail8", "tail8");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(passiveAmount));
    }

    @Override
    public void applyFocus() {
        super.applyFocus();
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (passiveAmount == 1) {
            description = DESC[0];
        }
        else {
            description = DESC[1] + passiveAmount + DESC[2];
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail8();
    }
}
