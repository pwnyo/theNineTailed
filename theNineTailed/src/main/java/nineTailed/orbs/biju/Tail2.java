package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;


public class Tail2 extends BijuTail {
    public Tail2() {
        super(1,"Tail2", "tail2");
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        gainPower(p, new FlameBarrierPower(p, passiveAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail2();
    }
}
