package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.powers.MayhemPower;


public class Tail10 extends BijuTail {
    public Tail10() {
        super("Tail10", "tail-10");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new MayhemPower(p, passiveAmount));
    }
}
