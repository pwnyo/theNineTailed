package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.VulnerablePower;


public class Tail5 extends BijuTail {
    public Tail5() {
        super(1,"Tail5", "tail5");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, passiveAmount, false)));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail5();
    }
}
