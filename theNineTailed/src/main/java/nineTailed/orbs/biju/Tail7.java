package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.WeakPower;


public class Tail7 extends BijuTail {
    public Tail7() {
        super(1,"Tail7", "tail7");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, passiveAmount, false)));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail7();
    }
}
