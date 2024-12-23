package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.FlameBarrierPower;


public class Tail2 extends BijuTail {
    public Tail2() {
        super("TwoTail", "tail-2",2,1);
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new FlameBarrierPower(p, passiveAmount));
    }
}
