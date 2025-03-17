package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.MayhemPower;


public class Tail10 extends BijuTail {
    public Tail10() {
        super(1,2,"Tail10", "tail10");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new MayhemPower(p, passiveAmount));
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESC[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail10();
    }
}
