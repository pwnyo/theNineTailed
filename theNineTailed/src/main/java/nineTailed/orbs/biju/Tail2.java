package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.FlameBarrierPower;


public class Tail2 extends BijuTail {
    public Tail2() {
        super(2,"Tail2", "tail-2");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new FlameBarrierPower(p, passiveAmount));
    }
}
